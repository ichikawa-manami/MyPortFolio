package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.entity.LearningInfo;
import com.example.demo.repository.UserInfoRepository;

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


}