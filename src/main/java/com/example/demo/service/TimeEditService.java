package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.TimeEditRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeEditService {
	private final UserInfoMapper userInfoMapper;
	
    public void timeedit(TimeEditRequest timeEditRequest) {
        
        userInfoMapper.timeedit(timeEditRequest);
    }

}
