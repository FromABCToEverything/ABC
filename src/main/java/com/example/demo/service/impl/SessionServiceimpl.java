package com.example.demo.service.impl;

import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.WordSetEntity;
import com.example.demo.mapper.SessionMapper;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SessionServiceimpl implements SessionService {

    @Autowired
    private SessionMapper session;
    @Override
    public List<SessionEntity> getUser() {
        return session.getUser();
    }

    @Override
    public String findOpen_id(String session_id) {
        return session.findOpen_id(session_id);
    }

    @Override
    public int add_vocabulary(String sql) {
        return session.add_vocabulary(sql);
    }

    @Override
    public int add_process(String sql) {
        return session.add_process(sql);
    }

    @Override
    public int add_plan(String sql) {
        return session.add_plan(sql);
    }

    @Override
    public List<WordSetEntity> select_plan(String sql) {
        return session.select_plan(sql);
    }

    @Override
    public List<Map<String,Object>> select_1(String sql) {

        return session.select_1(sql);
    }

    @Override
    public void resetSession(String sql) {
        session.droptable();
        session.resetSession(sql);
    }


}
