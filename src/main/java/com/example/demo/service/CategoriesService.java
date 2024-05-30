package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoMapper;
import com.example.demo.entity.CategoryInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoriesService {

	
    private final UserInfoMapper userInfoMapper;

//項目追加ページでカテゴリー名を表示させる
	    public CategoryInfo findByCategoryName(Long category_id) {
	    	return userInfoMapper.findByCategoryName(category_id);
	    }
	    

}