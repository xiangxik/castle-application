package kim.castle.core.support.json;

import java.io.IOException;

import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectErrorSerializer extends JsonObjectSerializer<ObjectError> {

	@Override
	protected void serializeObject(ObjectError value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {
		jgen.writeStringField("objectName", value.getObjectName());
		jgen.writeStringField("code", value.getCode());
		jgen.writeStringField("defaultMessage", value.getDefaultMessage());

		if (value instanceof FieldError) {
			jgen.writeStringField("field", ((FieldError) value).getField());
			jgen.writeObjectField("rejectedValue", ((FieldError) value).getRejectedValue());
		}
	}

}
