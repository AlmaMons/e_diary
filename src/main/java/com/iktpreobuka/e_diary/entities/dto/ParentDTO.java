package com.iktpreobuka.e_diary.entities.dto;

import java.util.Date;

import com.iktpreobuka.e_diary.entities.ParentEntity;

public class ParentDTO {

	private Long id;
	private String name;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String jmbg;
	private String email;
	private Date birthDate;

	public ParentDTO() {
		super();
	}

	public ParentDTO(Long id, String name, String lastName, String address,
			String phoneNumber, String jmbg, String email, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.jmbg = jmbg;
		this.email = email;
		this.birthDate = birthDate;
	}

	public ParentDTO(ParentEntity parent) {
		this.id = parent.getId();
		this.name = parent.getName();
		this.lastName = parent.getLastName();
		this.address = parent.getAddress();
		this.phoneNumber = parent.getPhoneNumber();
		this.jmbg = parent.getJmbg();
		this.email = parent.getJmbg();
		this.birthDate = parent.getBirthDate();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getJmbg() {
		return jmbg;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
