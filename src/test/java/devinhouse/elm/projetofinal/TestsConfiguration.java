package devinhouse.elm.projetofinal;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class TestsConfiguration {

	@Bean
	public MappingJackson2HttpMessageConverter mockFriendlyHttpMessageConverter() {

		var skipMockitoObjectsIntrospector = new JacksonAnnotationIntrospector() {
			@Override
			public boolean hasIgnoreMarker(final AnnotatedMember m) {
				return super.hasIgnoreMarker(m) || m.getName().contains("Mockito");
			}
		};

		var objectMapper = new Jackson2ObjectMapperBuilder()
			.annotationIntrospector(skipMockitoObjectsIntrospector)
			.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS).build();

		return new MappingJackson2HttpMessageConverter(objectMapper);
	}
}