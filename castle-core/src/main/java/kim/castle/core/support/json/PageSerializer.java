package kim.castle.core.support.json;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * {@linkplain Page}的序列化器。
 * 
 * @作者 孔祥溪
 * @创建时间 2015年10月5日 下午10:25:30
 * @param <P>
 * @param <T>
 * @param <I>
 */
public class PageSerializer extends JsonObjectSerializer<Page<?>> {

	public static final String DEFAULT_TOTAL_FIELD = "total";
	public static final String DEFAULT_CURRENT_FIELD = "current";
	public static final String DEFAULT_ROWCOUNT_FIELD = "rowCount";
	public static final String DEFAULT_ROWS_FIELD = "rows";

	@Override
	protected void serializeObject(Page<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeNumberField(DEFAULT_CURRENT_FIELD, value.getNumber() + 1);
		jgen.writeNumberField(DEFAULT_ROWCOUNT_FIELD, value.getSize());
		jgen.writeFieldName(DEFAULT_ROWS_FIELD);
		provider.findValueSerializer(List.class, null).serialize(value.getContent(), jgen, provider);
		jgen.writeNumberField(DEFAULT_TOTAL_FIELD, value.getTotalElements());
	}

}
