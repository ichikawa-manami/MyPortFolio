package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void save(UserAddRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);
        // データベースに保存する処理を追加
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(userRequest.getEmail());
        userInfo.setPassword(encodedPassword);
        
        userInfoMapper.save(userRequest);
    }
}