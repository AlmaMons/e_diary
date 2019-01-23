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
import javax.persistence.Version;

@Entity
@Table (name = "school_year")
public class SchoolYearEntity {
	
	private Long id;
	private Integer version;
	private String code;
	
	private String year;
	
	private List<ClassEntity> classes = new ArrayList<>();
	private List<SemesterEntity> semesters = new ArrayList<>();
	private List<SubjectEntity> subjects = new ArrayList<>();
	
	
	public SchoolYearEntity() {
		super();
	}
	
	public SchoolYearEntity(String year, List<ClassEntity> classes, List<SemesterEntity> semesters,
			List<SubjectEntity> subjects) {
		super();
		this.year = year;
		this.classes = classes;
		this.semesters = semesters;
		this.subjects = subjects;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "school_year_id")
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
	
	@Column (name = "year")
	public String getYear() {
		return year;
	}
	
	@OneToMany (mappedBy = "schoolYear", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	public List<ClassEntity> getClasses() {
		return classes;
	}
	
	@OneToMany (mappedBy = "schoolYear", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	public List<SemesterEntity> getSemesters() {
		return semesters;
	}
	
	@OneToMany (mappedBy = "schoolYear", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	public List<SubjectEntity> getSubjects() {
		return subjects;
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
	public void setYear(String year) {
		this.year = year;
	}
	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}
	public void setSemesters(List<SemesterEntity> semesters) {
		this.semesters = semesters;
	}
	public void setSubjects(List<SubjectEntity> subjects) {
		this.subjects = subjects;
	}
	
	
	


}
