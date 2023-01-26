// Source : https://stackoverflow.com/questions/65807229/how-can-i-use-the-modelmapper-in-spring

package ch.heig.icecreams.api.configuration;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.entities.IceCreamEntity;
import ch.heig.icecreams.api.entities.OriginEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.openapitools.model.IceCreamDTOid;
import org.openapitools.model.IceCreamDTOobj;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        Converter<List<Integer>, List<ContainerEntity>> idToContainer = ctx -> ctx.getSource().stream()
                .map(id -> {
                    var container = new ContainerEntity();
                    container.setId(id);
                    return container;
                })
                .toList();
        var modelMapper = new ModelMapper();
        modelMapper
                .typeMap(IceCreamDTOid.class, IceCreamEntity.class)
                .addMappings(mapper -> mapper.using(idToContainer)
                        .map(IceCreamDTOid::getContainerIds, IceCreamEntity::setContainers));
        return modelMapper;
    }
}