package com.example.demo.entity;

import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="categories")
public class CategoryInfo implements Serializable {
	
	@Id
	private Long id;
	
	private String name;
	
	private String created_at;
	
	private String updated_at;
}

