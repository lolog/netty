package net.netty.plugins.pojo;

import java.io.Serializable;

public class People implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	private String info;
	public People() {
		// TODO Auto-generated constructor stub
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + ", info=" + info + "]";
	}
}
