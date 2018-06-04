package kim.castle.support.data;

import java.util.List;

/**
 * 层次
 * 
 * @author ken
 *
 * @param <T>
 */
public interface Hierarchical<T> extends Sortable {

	public T getParent();

	public void setParent(T parent);

	public List<T> getChildren();

	public void setChildren(List<T> children);
}
