// Sources : https://stackoverflow.com/questions/65807229/how-can-i-use-the-modelmapper-in-spring

package ch.heig.icecreams.api.configuration;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import ch.heig.icecreams.api.entities.OriginEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.openapitools.model.ContainerDTOid;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.OriginDTOid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        // Création des converters pour les relations ManyToMany
        Converter<List<Integer>, List<ContainerEntity>> idToContainer = ctx -> ctx
                .getSource()
                .stream()
                .map(id -> {
                    var container = new ContainerEntity();
                    container.setId(id);
                    return container;
                })
                .toList();
        Converter<List<Integer>, List<IceCreamEntity>> idToIceCream = ctx -> ctx
                .getSource()
                .stream()
                .map(id -> {
                    var iceCream = new IceCreamEntity();
                    iceCream.setId(id);
                    return iceCream;
                })
                .toList();

        var modelMapper = new ModelMapper();

        // Ajout du mapping pour la relation OneToMany
        modelMapper
                .typeMap(OriginDTOid.class, OriginEntity.class)
                .addMappings(mapper -> mapper
                        .using(idToIceCream)
                        .map(OriginDTOid::getIceCreamIds, OriginEntity::setIceCreams)
                );

        // Ajout des mappings pour les relations ManyToMany
        modelMapper
                .typeMap(IceCreamDTOid.class, IceCreamEntity.class)
                .addMappings(mapper -> mapper
                        .using(idToContainer)
                        .map(IceCreamDTOid::getContainerIds, IceCreamEntity::setContainers)
                );
        modelMapper
                .typeMap(ContainerDTOid.class, ContainerEntity.class)
                .addMappings(mapper -> mapper
                        .using(idToIceCream)
                        .map(ContainerDTOid::getIceCreamIds, ContainerEntity::setIceCreams)
                );
        return modelMapper;
    }
}