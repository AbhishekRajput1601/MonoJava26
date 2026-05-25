package org.abhishek.studentmanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_id")
	private Long sId;

	@NotBlank(message = "Student name is required")
	@Size(min = 2, max = 100, message = "Student name must be between 2 and 100 characters")
	@Column(name = "s_name", nullable = false, length = 100)
	private String sName;

	@NotNull(message = "Student age is required")
	@Min(value = 1, message = "Student age must be at least 1")
	@Max(value = 120, message = "Student age must be less than or equal to 120")
	@Column(name = "s_age", nullable = false)
	private Integer sAge;

	@NotBlank(message = "Department is required")
	@Size(min = 2, max = 100, message = "Department must be between 2 and 100 characters")
	@Column(name = "s_department", nullable = false, length = 100)
	private String sDepartment;

	public Student() {
	}

	public Student(String sName, Integer sAge, String sDepartment) {
		this.sName = sName;
		this.sAge = sAge;
		this.sDepartment = sDepartment;
	}

	public Long getSId() {
		return sId;
	}

	public void setSId(Long sId) {
		this.sId = sId;
	}

	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public Integer getSAge() {
		return sAge;
	}

	public void setSAge(Integer sAge) {
		this.sAge = sAge;
	}

	public String getSDepartment() {
		return sDepartment;
	}

	public void setSDepartment(String sDepartment) {
		this.sDepartment = sDepartment;
	}
}
