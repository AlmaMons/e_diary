package com.iktpreobuka.e_diary.entities;

import java.util.Date;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.e_diary.enumerations.EMarkType;

@Entity
@Table (name = "mark")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MarkEntity {

	private Long id;
	private Integer version;
	private String code;
	
	private Integer mark;
	private Date date;
	private EMarkType markType;
	
	private StudentEntity student;
	private TeacherEntity teacher;
	private SubjectEntity subject;
	private SemesterEntity semester;
	

	public MarkEntity() {
		super();
	}
	
	public MarkEntity(Integer mark, Date date, EMarkType markType, StudentEntity student, TeacherEntity teacher,
			SubjectEntity subject, SemesterEntity semester) {
		super();
		this.mark = mark;
		this.date = date;
		this.markType = markType;
		this.student = student;
		this.teacher = teacher;
		this.subject = subject;
		this.semester = semester;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "mark_id")
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
	
	@Column (name = "mark")
	public Integer getMark() {
		return mark;
	}
	
	@Column (name = "date")
	public Date getDate() {
		return date;
	}
	
	@Column (name = "mark_type")
	public EMarkType getMarkType() {
		return markType;
	}
	
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "student")
	public StudentEntity getStudent() {
		return student;
	}
	
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "teacher")
	public TeacherEntity getTeacher() {
		return teacher;
	}
	
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "subject")
	public SubjectEntity getSubject() {
		return subject;
	}

	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "semester")
	public SemesterEntity getSemester() {
		return semester;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setMarkType(EMarkType markType) {
		this.markType = markType;
	}
	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}
	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
	public void setSemester(SemesterEntity semester) {
		this.semester = semester;
	}
	
	
	

	
	
}
