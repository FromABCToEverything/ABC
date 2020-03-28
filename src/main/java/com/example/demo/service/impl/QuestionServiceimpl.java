package com.example.demo.service.impl;

import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.QuestionSetEntity;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceimpl implements QuestionService {
    @Autowired
    private QuestionMapper question;
    @Override
    public List<QuestionEntity> findQusetion(int question_id )
    {
        List<QuestionEntity> list=question.findQusetion(question_id);
        return list;
    }

    @Override
    public List<QuestionEntity> findQuestion_set(String open_id,@Param("sort")String sort,@Param("order") String order)
    {
        List<QuestionEntity> list=question.findQuestion_set(open_id,sort,order);
        return list;
    }

    @Override
    public List<QuestionEntity> findQuestion_list(int book_id,String open_id) {
//        String table_name="question_temp_"+book_id;
//        String table_name_open_id="question_temp_"+open_id+"_"+book_id;
//        String table_word_name="word_temp_"+open_id+"_"+book_id;
        //question.create_table(table_name,table_name_open_id);
        List<QuestionEntity> list= question.findQuestion_list(book_id,open_id);
        return list;
    }

    @Override
    public List<QuestionEntity> findQuestion_list_unlogin(int book_id) {
//        String table_name="question_temp_"+book_id;
//        question.create_table_unlogin(table_name);
//        int a=question.insertTemp(table_name,book_id);
//        System.out.println(a+"这是插入");
//
//        List<QuestionEntity> list=question.findQuestion_list_unlogin(table_name,book_id);
        return question.findQuestion_list_un(book_id);

    }

    @Override
    public int insert_question(String type,int point_id, String stem, String choices, String answer, String explanation) {
        int op=question.insert_question(type,point_id, stem, choices, answer, explanation);
        if(op!=0)
            op=1;
        else
            op=0;
        return op;
    }

    @Override
    public int insert_question_set_map(int point_id, String open_id) {
        int op=question.insert_question_set_map(point_id, open_id);
        if(op!=0)
            op=1;
        else
            op=0;
        return op;
    }

    @Override
    public List<QuestionSetEntity> question_set(int set_id) {
        return question.question_set(set_id);
    }

    @Override
    public List<QuestionEntity> question(int set_id) {
        return question.question(set_id);
    }


}
