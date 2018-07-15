package kim.castle.core.support.json;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.databind.JsonSerializer;

import kim.castle.core.support.data.Node;
import kim.castle.core.support.data.Tree;

@Configuration
public class ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
		jacksonObjectMapperBuilder.modules(jpaModule());
		jacksonObjectMapperBuilder.serializerByType(Page.class, new PageSerializer());
		jacksonObjectMapperBuilder.serializerByType(Tree.class, new TreeSerializer<>());
		jacksonObjectMapperBuilder.serializerByType(Node.class, nodeSerializer());
		jacksonObjectMapperBuilder.serializerByType(ObjectError.class, new ObjectErrorSerializer());
	}

	@Bean
	public JsonSerializer<?> nodeSerializer() {
		return new NodeSerializer<>();
	}

	@Bean
	public JpaModule jpaModule() {
		return new JpaModule();
	}

}
