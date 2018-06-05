package kim.castle.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import ch.mfrey.jackson.antpathfilter.AntPathFilterMixin;
import kim.castle.support.data.Node;
import kim.castle.support.data.Tree;
import kim.castle.support.json.JpaModule;
import kim.castle.support.json.NodeSerializer;
import kim.castle.support.json.ObjectErrorSerializer;
import kim.castle.support.json.PageSerializer;
import kim.castle.support.json.TreeSerializer;

@Configuration
public class JsonConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(simpleModule());
		objectMapper.registerModule(jpaModule());

		objectMapper.addMixIn(Persistable.class, AntPathFilterMixin.class);

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);

		return objectMapper;
	}

	@Bean
	public SimpleModule simpleModule() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Page.class, new PageSerializer<>());
		simpleModule.addSerializer(Tree.class, new TreeSerializer<>());
		simpleModule.addSerializer(Node.class, new NodeSerializer<>());
		simpleModule.addSerializer(ObjectError.class, new ObjectErrorSerializer());
		return simpleModule;
	}

	@Bean
	public JpaModule jpaModule() {
		return new JpaModule();
	}
}
