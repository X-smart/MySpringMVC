package com.example.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class User {

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@NotEmpty(message="姓名不能为空！")
	private String name;
	private Integer age;
	
	private String sex;
	
	private String xingqu;
	
	@Size(min=11,max=11,message="手机号必须是11位！")
	private String phoneNumber;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getXingqu() {
		return xingqu;
	}
	public void setXingqu(String xingqu) {
		this.xingqu = xingqu;
	}
	
	public User(String id, String name, Integer age, String sex, String xingqu, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.xingqu = xingqu;
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", xingqu=" + xingqu
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	public User() {
		super();
		
	}
	
}
