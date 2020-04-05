package com.example.demo.controller;

import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.QuestionSetEntity;
import com.example.demo.service.impl.QuestionServiceimpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.DemoApplication.sessionMap;

@RestController
public class QuestionController {

    @Autowired
    private QuestionServiceimpl question;

    @GetMapping(path = "/question")
    public Object findQuestion(int question_id)
    {
        List<QuestionEntity>list=question.findQusetion(question_id);
        return list;
    }

//    @GetMapping(path = "/question_list")
//    public List<QuestionEntity> findQuestion_list(String session_id,int book_id)
//    {
//        String open_id= (String) sessionMap.get(session_id);
//        List<QuestionEntity> list=null;
//        if(open_id!=null)
//        //要先判断是否登录
//        { list=question.findQuestion_list(book_id,open_id);}
//        else
//        list=question.findQuestion_list_unlogin(book_id);
//        return list;
//    }

    @PostMapping("/question")
    public String insert_Question(String type,int point_id,String stem,String choices,String answer,String explanation,String session_id)
    {
        String open_id= (String) sessionMap.get(session_id);
        int op = question.insert_question(type, point_id, stem, choices, answer, explanation);
        if (op == 1) {
            op=question.insert_question_set_map(point_id, open_id);
            if(op==1)
            {
                //插入question成功，插入question_set_map成功，op=1
                return "success";
            }
            else
                {
                    //插入question成功，插入question_set_map失败，op=0
                    return "success question,error set_map";
                }
        } else {
            //向客户端返回插入问题失败，op=0
            return "error";
        }


    }

    @GetMapping(path = "/question_set")
    public List<QuestionEntity> question_set(int set_id,int page)
    {
//        int pageSize=20;
//        int index=(page-1)*pageSize;
        return question.question_set(set_id,page);
    }

//    @GetMapping(path = "/question_list1")//这是list界面显示的
//    public List<QuestionEntity> question(int set_id)
//    {
//        return question.question(set_id);
//    }




}
