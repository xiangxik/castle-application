package kim.castle.support.json;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * jpa实体反序列化器
 * 
 * @作者 孔祥溪
 * @创建时间 2015年10月5日 下午10:24:08
 * @param <T>
 * @param <I>
 */
public class JpaEntityDeserializer<T, I extends Serializable> extends JsonDeserializer<T> {

	private JpaEntityInformation<?, ?> entityInformation;
	private EntityManager entityManager;

	public JpaEntityDeserializer(JpaEntityInformation<?, ?> entityInformation, EntityManager entityManager) {
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		I identifier = (I) p.readValueAs(entityInformation.getIdType());
		return identifier == null ? null : (T) entityManager.getReference(entityInformation.getJavaType(), identifier);
	}

}
