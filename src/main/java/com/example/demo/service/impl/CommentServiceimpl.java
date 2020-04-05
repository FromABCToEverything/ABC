package com.example.demo.service.impl;

import com.example.demo.entity.CommentEntity;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceimpl implements CommentService {
    @Autowired
    private CommentMapper comment;
    @Override
    public List<CommentEntity> search_comment(String type, int to_id,int page) {
        List<CommentEntity> list=comment.search_comment(type, to_id,page);
        return list;
    }

    @Override
    public int post_comment(String type, int to_id, String open_id, int score, String content) {
        int op=comment.insertComment_ifnotexist(type,to_id,open_id);//此op为1

        if(op!=0)//说明之前并不存在此人评论
        {
            op=comment.update_comment_noexist(type,to_id,score);
        }else
        {
            op=comment.update_comment_exist(type, to_id, open_id, score, content);

        }
        if(op!=0)
            op=1;//并返回成功消息给客户端
        else
            op=0;
        return op;
    }
}
