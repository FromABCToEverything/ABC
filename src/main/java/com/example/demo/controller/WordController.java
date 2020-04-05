package com.example.demo.controller;

import com.example.demo.entity.WordEntity;
import com.example.demo.service.impl.WordServiceimpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.example.demo.DemoApplication.sessionMap;

@RestController
public class WordController {
    @Autowired
    private WordServiceimpl word;

    @GetMapping(path = "/word_set")
    public Object findWord(String session_id)
    {
        String open_id= (String) sessionMap.get(session_id);
        List<WordEntity>list=word.findWord(open_id);
        return list;
    }

    @GetMapping(path = "/book_number")
    public Map<String,Integer> findCount(String session_id,Integer book_id)
    {//这里需要判断是否登录
        String open_id= (String) sessionMap.get(session_id);
        int count=0;
        Map<String,Integer> map=null;

        if(open_id!=null)
        {map=word.findCount(open_id,book_id.toString());}
        else
        map=word.findCount_unlogin(book_id);
        return map;
    }
    @GetMapping(path="/word_list")
    public List<WordEntity> findWG_list(String session_id, int book_id)
    {//同上，需要判断是否登录，返回一本书中的单词
        String open_id= (String) sessionMap.get(session_id);
        if(open_id!=null)
        return word.finW_list(open_id,book_id);
        else {
            System.out.println("未登录");
            return word.findW_list_unlogin(book_id);
        }

    }

//    @GetMapping(path = "wordnum")
//    public int wordnum(String session_id,Integer book_id)
//    {
//        String open_id= (String) sessionMap.get(session_id);
//        return word.Count(open_id,book_id);
//    }

    @GetMapping(path = "/word")
    public List<WordEntity> word(int word_id)
    {
        return word.word(word_id);
    }

    @GetMapping(path = "/word_inBook")
    public List<WordEntity> in_book(int book_id,int page)
    {
//        int pageSize=20;
//        int index=(page-1)*pageSize;

        return word.findWord_in_book(book_id,page);
    }

    @GetMapping(path = "/word_set_list")
    public List<WordEntity> word_set(int set_id,int page)
    {
//        int pageSize=20;
//        int index=(page-1)*pageSize;
        return word.word_set(set_id,page);

    }


}
