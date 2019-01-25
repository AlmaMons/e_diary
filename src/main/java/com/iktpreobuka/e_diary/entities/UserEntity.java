package com.iktpreobuka.e_diary.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class UserEntity {

	private Long id;
	private Integer version;
	private String code;

	private String email;
	private String password;
	private String name;
	private String lastName;

	private RoleEntity role;
	private PersonEntity person;

	public UserEntity() {
		super();
	}

	public UserEntity(Integer version, String code, String email, String name, String lastName, RoleEntity role, PersonEntity person) {
		super();
		this.version = version;
		this.code = code;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
		this.person = person;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	public Long getId() {
		return id;
	}

	@Version
	@Column (name = "version")
	public Integer getVersion() {
		return version;
	}
	
	@Column (name = "code")
	public String getCode() {
		return code;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	//DTO
	@JsonIgnore
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	public RoleEntity getRole() {
		return role;
	}

	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "person")
	public PersonEntity getPerson() {
		return person;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setPerson(PersonEntity person) {
		this.person = person;
	}

}
