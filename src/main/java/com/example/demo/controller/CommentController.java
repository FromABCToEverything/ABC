package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.service.impl.CommentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentServiceimpl comment;
    @GetMapping("/comment")
    public Object search_comment(String type, Integer to_id)
    {
        List<CommentEntity> list=comment.search_comment(type, to_id);
        return list;
    }
     @PostMapping("/comment")
     public String post_comment(String type, Integer to_id, String open_id, Integer score, String content)
     {
         int op=comment.post_comment(type, to_id, open_id, score, content);
         if(op==1)
             return "success";
         else
             return "error";


     }
}
