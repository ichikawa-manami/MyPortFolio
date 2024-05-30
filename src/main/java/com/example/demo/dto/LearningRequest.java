package com.example.demo.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LearningRequest implements Serializable {
	
 private Long id;
	
// @UniqueElements(message = "（入力した項目名）は既に登録されています")
 @NotEmpty(message = "項目名は必ず入力してください")
 @Size(max = 50, message = "50文字以内で入力してください")
	private String name;
	
 @NotEmpty(message = "学習時間は必ず入力してください")
 @Size(min = 1, message = "学習時間は1以上の数字で入力してください")
	private String study_time;
	
	private String month;
	
	private String category_id;
	
	private String user_id;
	
	private String created_at;
	
	private String updated_at;

}
