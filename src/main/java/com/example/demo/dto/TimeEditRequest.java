package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TimeEditRequest implements Serializable{

	private Long id;
	
	private int studyTime;
}