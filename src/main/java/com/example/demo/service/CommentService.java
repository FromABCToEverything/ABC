package com.example.demo.service;

import com.example.demo.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> search_comment(String type, int to_id);
    int post_comment(String type,int to_id,String open_id,int score,String content);




}
