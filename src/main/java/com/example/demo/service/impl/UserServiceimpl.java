package com.example.demo.service.impl;

import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper user;

    @Override
    public int insertUser(String openid, String avatar_url, String name) {
        return user.insertUser(openid, avatar_url, name);
    }




}
