package com.example.demo.service.impl;

import com.example.demo.entity.SessionEntity;
import com.example.demo.mapper.SessionMapper;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SessionServiceimpl implements SessionService {

    @Autowired
    private SessionMapper session;
    @Override
    public List<SessionEntity> getUser() {
        return session.getUser();
    }
}
