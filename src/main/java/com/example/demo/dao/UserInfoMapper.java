package com.example.demo.dao;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserAddRequest;
import com.example.demo.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	 void save(UserAddRequest userAddRequest);
	   
}
