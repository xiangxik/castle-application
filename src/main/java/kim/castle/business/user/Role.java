package kim.castle.business.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kim.castle.support.jpa.DataEntity;

@Entity
@Table(name = "tbl_sys_role")
public class Role extends DataEntity<User, Long> {

	@NotNull
	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@NotNull
	@Column(length = 50, nullable = false, unique = true)
	private String code;

	@Size(max = 500)
	@Column(length = 500)
	private String description;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
