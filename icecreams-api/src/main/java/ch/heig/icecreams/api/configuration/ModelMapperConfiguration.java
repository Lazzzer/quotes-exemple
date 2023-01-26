// Source : https://stackoverflow.com/questions/65807229/how-can-i-use-the-modelmapper-in-spring

package ch.heig.icecreams.api.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
