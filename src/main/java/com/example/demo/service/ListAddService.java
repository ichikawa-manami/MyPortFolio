package com.example.demo.service;

import org.springframework.stereotype.Service;


import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.LearningRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListAddService {
	
    private final UserInfoMapper userInfoMapper;

//    private final LearningInfoRepository learningInfoRepository;

    public void add(LearningRequest learningRequest){
    	
        userInfoMapper.add(learningRequest);
    }

	/*    @Transactional(readOnly = true)
		public String findSkillName(String name) {
			return userInfoMapper.findSkillName(name);
		}*/
}