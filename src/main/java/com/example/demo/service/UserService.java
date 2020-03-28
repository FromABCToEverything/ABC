package com.example.demo.service;

import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.UserEntity;

import java.util.List;


public interface UserService {
    int insertUser(String openid,String avatar_url,String name);


}
