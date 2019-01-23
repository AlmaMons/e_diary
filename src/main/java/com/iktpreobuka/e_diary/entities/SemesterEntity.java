package com.iktpreobuka.e_diary.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "semester")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SemesterEntity {
	
	private Long id;
	private Integer version;
	private String code;
	
	private Integer orderNumber;
	private Date dateFrom;
	private Date dateTo;
	
	private SchoolYearEntity schoolYear;
	private List<MarkEntity> marks = new ArrayList<>();
	
	
	public SemesterEntity() {
		super();
	}
	
	public SemesterEntity(Integer orderNumber, Date dateFrom, Date dateTo, SchoolYearEntity schoolYear,
			List<MarkEntity> marks) {
		super();
		this.orderNumber = orderNumber;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.schoolYear = schoolYear;
		this.marks = marks;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name= "semester_id")
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
	
	@Column (name = "order_number")
	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	@Column (name = "date_from")
	public Date getDateFrom() {
		return dateFrom;
	}
	
	@Column (name = "date_to")
	public Date getDateTo() {
		return dateTo;
	}
	
	@JsonIgnore
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn (name = "school_year")
	public SchoolYearEntity getSchoolYear() {
		return schoolYear;
	}
	
	@OneToMany (mappedBy = "semester", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	public List<MarkEntity> getMarks() {
		return marks;
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
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public void setSchoolYear(SchoolYearEntity schoolYear) {
		this.schoolYear = schoolYear;
	}
	public void setMarks(List<MarkEntity> marks) {
		this.marks = marks;
	}
	
	

}
