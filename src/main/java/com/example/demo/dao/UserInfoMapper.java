package com.example.demo.dao;
import java.util.List;
import java.util.Optional;




import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.IntroEditRequest;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.entity.LearningInfo;
import com.example.demo.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	 void save(UserAddRequest userAddRequest);

	public UserInfo findByEmail(String email);
	
	 void edit(IntroEditRequest introEditRequest);
	 
		public LearningInfo findBySkillName(String name);

		List<LearningInfo> findAll();
		
		LearningInfo findId(Long id);

		public LearningInfo findById(Long id);
}
