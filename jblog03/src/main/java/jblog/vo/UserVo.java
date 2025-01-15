package jblog.vo;

import jakarta.validation.constraints.Size;

public class UserVo {
	
	@Size(min = 2, max = 8)
	private String id;

	@Size(min = 2, max = 8)
	private String name;

	@Size(min = 4, max = 16)
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
}
