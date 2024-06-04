package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.entity.LearningInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LearningInfoService {

    private final UserInfoMapper userInfoMapper;
  

	 public List<LearningInfo> findAll() {
	        return userInfoMapper.findAll();
	    }


	    public LearningInfo findById(Long id) {
	        return userInfoMapper.findById(id);
	    }

	    public LearningInfo findBySkillName(String name,String studyTime) {
	    	return userInfoMapper.findBySkillName(name,studyTime);
	    }
	    
//	    重複チェック
	    public boolean isItemExist(String name) {

			LearningInfo existingItem = userInfoMapper.findCatName(name);
			return existingItem != null;
	}     
	    
//	    public LearningInfo chartTime(String studyTime) {
//	        return userInfoMapper.chartTime(studyTime);
//	    }
	    
	    public List<LearningInfo> getLearningData() {
	        return userInfoMapper.getLearningData();
	    }

}