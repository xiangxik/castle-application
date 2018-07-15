package kim.castle.core.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kim.castle.core.support.jpa.TreeEntity;
import kim.castle.core.user.User;

@Entity
@Table(name = "tbl_sys_menu_item")
public class MenuItem extends TreeEntity<User, Long, MenuItem> {

	@Size(max = 50)
	@NotNull
	@Column(length = 50)
	private String name;

	@Size(max = 50)
	@NotNull
	@Column(length = 50, unique = true)
	private String code;

	@Size(max = 50)
	@Column(length = 50)
	private String iconCss;

	@Size(max = 200)
	@Column(length = 200)
	private String href;

	@Size(max = 50)
	@Column(length = 50)
	private String target;

	@Size(max = 500)
	@Column(length = 500)
	private String description;

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

	public String getIconCss() {
		return iconCss;
	}

	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
