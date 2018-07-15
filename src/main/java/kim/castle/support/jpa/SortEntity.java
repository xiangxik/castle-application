package kim.castle.support.jpa;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.CompareToBuilder;

import kim.castle.support.data.Sortable;

@MappedSuperclass
public class SortEntity<U, I extends Serializable> extends DataEntity<U, I>
		implements Sortable, Comparable<SortEntity<U, I>> {

	private Integer sortNo;

	@Override
	public Integer getSortNo() {
		return sortNo;
	}

	@Override
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@Override
	public int compareTo(SortEntity<U, I> o) {
		return new CompareToBuilder().append(getSortNo(), o.getSortNo()).append(getId(), o.getId()).toComparison();
	}
}
