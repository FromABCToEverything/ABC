package com.example.demo.service;

import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.WordSetEntity;

import java.util.List;
import java.util.Map;

public interface SessionService {
    List<SessionEntity> getUser();
    String findOpen_id(String session_id);
    int add_vocabulary(String sql);
    int add_process(String sql);
    int add_plan(String sql);
    List<WordSetEntity> select_plan(String sql);
    List<Map<String,Object>> select_1(String sql);
    void resetSession(String sql);
}
