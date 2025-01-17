package jblog.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserVo {
	
	@Size(min = 2, max = 8)
	private String id;

	@Size(min = 2, max = 8)
	private String name;

	@Size(min = 4, max = 16)
	private String password;
	
	@NotNull(message = "동의하기를 눌러주세요")
	private String agreeProv;
	
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
	public String getAgreeProv() {
		return agreeProv;
	}
	public void setAgreeProv(String agreeProv) {
		this.agreeProv = agreeProv;
	}
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", agreeProv=" + agreeProv + "]";
	}
	
	
}
