package com.iktpreobuka.e_diary.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class RoleEntity {

	private Long id;
	private Integer version;
	private String code;

	private String name;

	private List<UserEntity> users = new ArrayList<>();

	public RoleEntity() {
		super();
	}

	public RoleEntity(Integer version, String code, String name, List<UserEntity> users) {
		super();
		this.version = version;
		this.code = code;
		this.name = name;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	public Long getId() {
		return id;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	//DTO
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
