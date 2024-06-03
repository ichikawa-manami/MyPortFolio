package com.example.demo.service;

import org.springframework.stereotype.Service;


import com.example.demo.dao.UserInfoMapper;
import com.example.demo.dto.DeleteRequest;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteService {

	private final UserInfoMapper userInfoMapper;
	
    public void delete(DeleteRequest deleteRequest) {
        
        userInfoMapper.delete(deleteRequest);
    }

}
