package com.iktpreobuka.e_diary.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherEntity extends PersonEntity {

	private List<SubjectEntity> subjects = new ArrayList<>();
	private List<MarkEntity> marks = new ArrayList<>();

	public TeacherEntity() {
		super();
	}

	public TeacherEntity(Integer version, String code, String name, String lastName, String address, String phoneNumber, String jmbg, String email,
			Date birthDate, List<UserEntity> users, List<SubjectEntity> subject, List<MarkEntity> marks) {
		super(version, code, name, lastName, address, phoneNumber, jmbg, email, birthDate, users);
		this.subjects = subject;
		this.marks = marks;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "subject_teacher", joinColumns = {
			@JoinColumn(name = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "subject_id", nullable = false, updatable = false) })
	public List<SubjectEntity> getSubjects() {
		return subjects;
	}

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	public List<MarkEntity> getMarks() {
		return marks;
	}

	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}

	public void setMarks(List<MarkEntity> marks) {
		this.marks = marks;
	}

}
