package devinhouse.elm.projetofinal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.modelmapper.ModelMapper;


@Configuration
public class GeneralConfiguration {

    @Bean
    public ModelMapper modelMapper() {
	return new ModelMapper();
    }
}
