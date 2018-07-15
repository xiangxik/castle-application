package kim.castle.support.jpa;

import java.io.Serializable;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class TreeEntityListener {

	/** 树路径分隔符 */
	private static final String TREE_PATH_SEPARATOR = ",";

	@PrePersist
	public <U, I extends Serializable, T> void prePersist(TreeEntity<U, I, T> entity) {
		@SuppressWarnings("unchecked")
		TreeEntity<U, I, T> parent = (TreeEntity<U, I, T>) entity.getParent();
		if (parent != null) {
			entity.setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
		} else {
			entity.setTreePath(TREE_PATH_SEPARATOR);
		}
	}

	@PreUpdate
	public <U, I extends Serializable, T> void preUpdate(TreeEntity<U, I, T> entity) {
		@SuppressWarnings("unchecked")
		TreeEntity<U, I, T> parent = (TreeEntity<U, I, T>) entity.getParent();
		if (parent != null) {
			entity.setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
		} else {
			entity.setTreePath(TREE_PATH_SEPARATOR);
		}
	}
}
