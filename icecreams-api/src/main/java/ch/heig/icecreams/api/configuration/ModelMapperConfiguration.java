// Sources : https://www.baeldung.com/java-modelmapper-lists

package ch.heig.icecreams.api.configuration;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import ch.heig.icecreams.api.entities.OriginEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.openapitools.model.ContainerDTOid;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.OriginDTOid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    // Création des converters pour les relations ManyToMany où nous créons un objet à partir de l'id
    static class IdsContainersConverter extends AbstractConverter<List<Integer>, List<ContainerEntity>> {
        @Override
        protected List<ContainerEntity> convert(List<Integer> containerIds) {
            return containerIds
                    .stream()
                    .map(id -> {
                        var container = new ContainerEntity();
                        container.setId(id);
                        return container;
                    })
                    .toList();
        }
    }

    static class IdsIceCreamsConverter extends AbstractConverter<List<Integer>, List<IceCreamEntity>> {
        @Override
        protected List<IceCreamEntity> convert(List<Integer> iceCreamIds) {
            return iceCreamIds
                    .stream()
                    .map(id -> {
                        var iceCream = new IceCreamEntity();
                        iceCream.setId(id);
                        return iceCream;
                    })
                    .toList();
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // Ajout du mapping pour la relation OneToMany
        modelMapper
                .typeMap(OriginDTOid.class, OriginEntity.class)
                .addMappings(mapper -> mapper
                        .using(new IdsIceCreamsConverter())
                        .map(OriginDTOid::getIceCreamIds, OriginEntity::setIceCreams)
                );

        // Ajout des mappings pour les relations ManyToMany
        modelMapper
                .typeMap(IceCreamDTOid.class, IceCreamEntity.class)
                .addMappings(mapper -> mapper
                        .using(new IdsContainersConverter())
                        .map(IceCreamDTOid::getContainerIds, IceCreamEntity::setContainers)
                );
        modelMapper
                .typeMap(ContainerDTOid.class, ContainerEntity.class)
                .addMappings(mapper -> mapper
                        .using(new IdsIceCreamsConverter())
                        .map(ContainerDTOid::getIceCreamIds, ContainerEntity::setIceCreams)
                );
        return modelMapper;
    }
}