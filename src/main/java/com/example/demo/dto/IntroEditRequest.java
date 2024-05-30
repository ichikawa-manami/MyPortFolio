package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IntroEditRequest implements Serializable{
	
	@NotEmpty(message = "自己紹介は必ず入力してください")
    @Size(min=50, max = 200, message = "50文字以上、200文字以下で入力してください。")
    private String self_introduction;
    
    private Long id;
}
