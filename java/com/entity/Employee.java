package com.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@Column(nullable = false)
	private String job;

	@Column(nullable = false)
	private Long salary;

	@Column(nullable = true)
	// @CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hiredate;
}
