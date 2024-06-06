package com.example.demo.service;

import org.springframework.stereotype.Service;


import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.IntroEditRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntroEditService {
	
    private final UserInfoMapper userInfoMapper;

    private final UserInfoRepository userInfoRepository;
    /**
     * ユーザ情報登録
     * @param userAddRequest リクエストデータ
     */
    public void edit(IntroEditRequest introEditRequest) {
       
        userInfoMapper.edit(introEditRequest);
    }
    
    
}