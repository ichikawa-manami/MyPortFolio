package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LearningRequest implements Serializable {
	
 private Long id;
	
	private String name;
	
	private String study_time;
	
	private String month;
	
	private String category_id;
	
	private String user_id;
	
	private String created_at;
	
	private String updated_at;

}
