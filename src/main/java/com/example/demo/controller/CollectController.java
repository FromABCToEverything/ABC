package com.example.demo.controller;

import com.example.demo.dto.myBookSetDto;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BookSetEntity;
import com.example.demo.service.impl.BookServiceimpl;
import com.example.demo.service.impl.CollectServiceimpl;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.Map;

import static com.example.demo.DemoApplication.sessionMap;

@RestController
public class CollectController {
    @Autowired
    private CollectServiceimpl collect;

    @GetMapping(path = "/collect_other")
    String if_collect_other(String type,int entry_id,String session_id)
    {
        String open_id= (String) sessionMap.get(session_id);
        return collect.if_collect_other(type, entry_id, open_id);
    }

    @GetMapping(path = "/collect_others")
    public Object showCollect_other(String session_id,String type,int page)
    {
        //type为B,W,Q,type_table为book,word...
        String open_id= (String) sessionMap.get(session_id);
        String type_map=null;
        String type_set=null;
        if(type.equals("W")){
            type_set="word_set";
            type_map="word_set_map";
        }
        else if(type.equals("B")){
            type_set="book_set";
            type_map="book_set_map";
        }
        else if(type.equals("N")){
            type_set="note_set";
            type_map="note_set_map";
        }
        else if(type.equals("Q")){
            type_set="question_set";
            type_map="question_set_map";
        }

        System.out.println(type_map+type_set);
        return collect.showCollect_other(open_id,type,type_set,page);
    }

    @GetMapping(path = "/collect_self")
    public Object showCollect_self(String session_id,String type,int page)
    {
        //int index=(page-1)*20;
        String open_id= (String) sessionMap.get(session_id);
        if(type.equals("W")) {
            System.out.println(type);
            return collect.showCollect_selfW(open_id,page);
        }
        else if(type.equals("B")) {

        return collect.showCollect_selfB(open_id,page);
        }
        else if (type.equals("Q")) {

            return   collect.showCollect_selfQ(open_id,page);
        }
        else if(type.equals("N")) {

          return collect.showCollect_selfN(open_id,page);
        }
        return 0;
    }
    @PostMapping(path = "/collect_element")
    public void updateCollect_element(String open_id,Integer set_id,Integer entry_id,String type)
    {
        String type_set=null;
        String type_map=null;
        if(type.equals("W")){
            type_set="word_set";
            type_map="word_set_map";
        }
        else if(type.equals("B")){
            type_set="book_set";
            type_map="book_set_map";
        }
        else if(type.equals("N")){
            type_set="note_set";
            type_map="note_set_map";
        }
        else if(type.equals("Q")){
            type_set="question_set";
            type_map="question_set_map";
        }

        int op=2;
        collect.insert_check(type_map,set_id,entry_id);
        collect.update_set(type_set, set_id, open_id);
//       if(op==1) {
//           collect.update_set(type_set, set_id, open_id);
//           //并且向客户端返回1
//       }
//       else
//       {
//           //do_nothing
//           //向客户端返回0
//       }
    }
    @PostMapping(path = "/collect_other")
    public String updateCollect_self(String session_id,String type,int set_id)
    {
        String open_id= (String) sessionMap.get(session_id);
        String type_set=null;
        if(type.equals("W")){
            type_set="word_set";
        }
         else if(type.equals("B")){
            type_set="book_set";
        }
        else if(type.equals("N")){
            type_set="note_set";
        }
        else if(type.equals("Q")){
            type_set="question_set";
        }

        int op=collect.insert_check2(open_id,type,set_id);

        if(op!=0) {
            collect.update_set_createdtime(type_set, set_id, open_id);
            return "success";
            //并且向客户端返回1
        }
        else
        {
            return "error";
            //向客户端返回0
        }
    }

    @PostMapping(path = "/collect_self")
    public String update_delete_self_set(String type, int session_id, String set_name, String creator_name, Spring private01)
    {
        String open_id= (String) sessionMap.get(session_id);
        String type_set=null;
        if(type.equals("W")){
            type_set="word_set";
        }
        else if(type.equals("B")){
            type_set="book_set";
        }
        else if(type.equals("N")){
            type_set="note_set";
        }
        else if(type.equals("Q")){
            type_set="question_set";
        }
        int op=collect.insert_check_self(type_set, open_id, set_name, creator_name, private01);
        if(op==1)
        {
            return "success";
        }
        else
        {//返回0给客户端
            return "error";
            }
    }
    @DeleteMapping(path = "/collect_self")
    public String delete_self(int set_id,String session_id)
    {
        String open_id= (String) sessionMap.get(session_id);
        int op=collect.delete_self(set_id, open_id);
        if(op==1)
        {
            //返回1
            return "success";
        }
        else
        {//返回0给客户端
            return "error";
        }
    }
    @GetMapping(path = "/collect_element")
    public List<myBookSetDto> search_collect(String type, int entry_id, String session_id)
    {
        String open_id= (String) sessionMap.get(session_id);
        System.out.println(collect.collect_element(type, entry_id, open_id));
        return collect.collect_element(type, entry_id, open_id);
    }



}
