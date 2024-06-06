package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="learning_data")
public class LearningInfo implements Serializable {
	
	@Id
	private Long id;

	private String name;
	
@Column(value="study_time")
	private String studyTime;

	private String month;
	
@Column(value="category_id")
	private Integer categoryId;
	
	private  Long user_id;
	
	private String created_at;
	
	private String updated_at;
}

