package kim.castle.busi.user;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kim.castle.support.jpa.DataEntity;

@Entity
public class User extends DataEntity<User, Long> {

	private String name;
	private String username;

	@JsonIgnore
	private String password;

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

}
