package com.example.demo.service;

import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.QuestionSetEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface QuestionService {

    List<QuestionEntity> findQusetion(int question_id);
    List<QuestionEntity> findQuestion_set(String open_id,@Param("sort")String sort,@Param("order") String order);
    List<QuestionEntity> findQuestion_list(int book_id,String open_id);
    List<QuestionEntity> findQuestion_list_unlogin(int book_id);
    int insert_question(String type,int point_id,String stem,String choices,String answer,String explanation);
    int insert_question_set_map(int point_id,String open_id);
    List<QuestionSetEntity> question_set(int set_id);

    List<QuestionEntity> question(int set_id);
}
