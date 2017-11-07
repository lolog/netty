package net.netty.client.pojo;

import org.msgpack.annotation.Message;

@Message
public class UserInfo{
	private String name;
	private Integer age;
	private String company;
	private String info;
	public UserInfo() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", age=" + age + ", company="
				+ company + ", info=" + info + "]";
	}
}
