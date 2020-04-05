package com.example.demo.controller;

import com.example.demo.entity.CommentEntity;
import com.example.demo.service.impl.CommentServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.DemoApplication.sessionMap;

@RestController
public class CommentController {
    @Autowired
    private CommentServiceimpl comment;
    @GetMapping("/comment")
    public Object search_comment(String type, Integer to_id,int page)
    {
        List<CommentEntity> list=comment.search_comment(type, to_id,page);
        return list;
    }
     @PostMapping("/comment")
     public String post_comment(String type, Integer to_id, String session_id, Integer score, String content)
     {
         String open_id= (String) sessionMap.get(session_id);
         int op=comment.post_comment(type, to_id, open_id, score, content);
         if(op==1)
             return "success";
         else
             return "error";


     }
}
