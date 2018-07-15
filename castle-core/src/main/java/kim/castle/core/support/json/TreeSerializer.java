package kim.castle.core.support.json;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Objects;

import kim.castle.core.support.data.Node;
import kim.castle.core.support.data.Tree;

public class TreeSerializer<T extends Tree<?>> extends JsonSerializer<T> {

	private boolean isChecked(Node<?> node, Set<?> checked) {
		if (checked != null) {
			for (Object c : checked) {
				if (Objects.equal(c, node.getData())) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		if (value.isCheckable()) {
			Set<?> checked = value.getChecked();
			Tree.visitNodes(value.getRoots(), node -> node.setChecked(isChecked(node, checked)));
		}
		if (value.isExpandAll()) {
			Tree.visitNodes(value.getRoots(), node -> node.setExpanded(true));
		}
		serializers.findValueSerializer(List.class, null).serialize(value.getRoots(), gen, serializers);

	}

}
