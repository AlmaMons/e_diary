package com.iktpreobuka.e_diary.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "student")
public class StudentEntity extends PersonEntity {

	private ParentEntity parent;
	private ClassEntity clas;
	private List<MarkEntity> marks = new ArrayList<>();
	
	
	public StudentEntity() {
		super();
	}

	public StudentEntity(Integer version, String code, String name, String lastName, String address, String phoneNumber, String jmbg, String email,
			Date birthDate, List<UserEntity> users, ParentEntity parent, ClassEntity clas, List<MarkEntity>mark) {
		super(version, code, name, lastName, address, phoneNumber, jmbg, email, birthDate, users);
		this.parent = parent;
		this.clas = clas;
		this.marks = mark;
	}
	//DTO
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "parent")
	public ParentEntity getParent() {
		return parent;
	}
	//DTO
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "clas")
	public ClassEntity getClas() {
		return clas;
	}
	
	@OneToMany (mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	public List<MarkEntity> getMark() {
		return marks;
	}

	public void setClas(ClassEntity clas) {
		this.clas = clas;
	}

	public void setMark(List<MarkEntity> mark) {
		this.marks = mark;
	}

	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}

	
	
	
	
}
