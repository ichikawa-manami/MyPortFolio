package com.example.demo.dto;


import java.io.Serializable;

import lombok.Data;

@Data
public class CategoriesRequest implements Serializable{

	private Long id;
	
	private String name;
}

	