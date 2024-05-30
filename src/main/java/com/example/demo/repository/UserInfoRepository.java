package com.example.demo.repository;
import java.util.Optional;


import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.LearningInfo;
import com.example.demo.entity.UserInfo;

@Repository

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

	  @Select("select * from users where email = #{email}")
    Optional<UserInfo> findByEmail(String email);
	  
	Optional<UserInfo> findByName(String name);
	
}
