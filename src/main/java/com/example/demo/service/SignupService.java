package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.entity.UserInfo;
import com.example.demo.dao.UserInfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {
	
    private final UserInfoMapper userInfoMapper;
    
    private final PasswordEncoder passwordEncoder;
    /**
     * ユーザ情報登録
     * @param userAddRequest リクエストデータ
     */
    public void save(UserAddRequest userAddRequest) {
        String encodedPassword = passwordEncoder.encode(userAddRequest.getPassword());
        userAddRequest.setPassword(encodedPassword);
        userInfoMapper.save(userAddRequest);
    }
}