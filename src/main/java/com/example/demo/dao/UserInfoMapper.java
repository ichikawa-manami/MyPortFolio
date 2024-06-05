package com.example.demo.dao;
import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.DeleteRequest;
import com.example.demo.dto.IntroEditRequest;
import com.example.demo.dto.LearningRequest;
import com.example.demo.dto.TimeEditRequest;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.entity.CategoryInfo;
import com.example.demo.entity.LearningInfo;
import com.example.demo.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	 void save(UserAddRequest userRequest);

	public UserInfo findByEmail(String email);
	
	 void edit(IntroEditRequest introEditRequest);
	 
		public LearningInfo findBySkillName(String name,String studyTime);

		List<LearningInfo> findAll();
		
		LearningInfo findId(Long id);

		public LearningInfo findById(Long id);
		
		void add(LearningRequest learningRequest);

		public CategoryInfo findByCategoryName(Long category_id);
		
		public LearningInfo findCatName(String name);
		
		void timeedit(TimeEditRequest timeEditRequest);
		
		void delete(DeleteRequest deleteRequest);
		
//		public LearningInfo chartTime(String studyTime);
		
		List<LearningInfo> getLearningData();
}
