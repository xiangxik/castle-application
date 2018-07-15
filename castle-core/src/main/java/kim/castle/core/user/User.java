package kim.castle.core.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kim.castle.core.support.jpa.DataEntity;

@Entity
@Table(name = "tbl_sys_user")
public class User extends DataEntity<User, Long> {

	public static final String USERNAME_PATTERN = "^$|^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{4,16}$";

	@NotNull
	@Size(min = 2, max = 10)
	private String name;

	@Size(min = 2, max = 20)
	@Pattern(regexp = USERNAME_PATTERN)
	@Column(nullable = false, unique = true, length = 50, updatable = false)
	private String username;

	@JsonIgnore
	@Size(min = 4, max = 20)
	@Column(nullable = false, length = 255)
	private String password;

	/** 头像 */
	@Column(length = 200)
	private String avatar;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinTable(name = "tbl_sys_user_role", joinColumns = {
			@JoinColumn(referencedColumnName = "id", name = "user_id") }, inverseJoinColumns = {
					@JoinColumn(referencedColumnName = "id", name = "role_id") })
	private Set<Role> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
