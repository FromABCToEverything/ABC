package com.example.demo.controller;

import com.example.demo.service.impl.CollectServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
public class CollectController {
    @Autowired
    private CollectServiceimpl collect;

    @GetMapping("/collect_other")
    public Object showCollect_other(String open_id,String type, String type_table)
    {//type为B,W,Q,type_table为book,word...
        String type_map=null;
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
        type_map=type_table+"_set_map";
        System.out.println(type_map+type_set);
        return collect.showCollect_other(open_id,type,type_set,type_map);
    }
    @GetMapping("/collect_self")
    public Object showCollect_self(String open_id,String type)
    {
        if(type.equals("W")) {
            System.out.println(type);
            return collect.showCollect_selfW(open_id);
        }
        else if(type.equals("B")) {

        return collect.showCollect_selfB(open_id);
        }
        else if (type.equals("Q")) {

            return   collect.showCollect_selfQ(open_id);
        }
        else if(type.equals("N")) {

          return collect.showCollect_selfN(open_id);
        }
        return 0;
    }
    @PostMapping("/collect_element")
    public void updateCollect_element(String open_id,Integer set_id,Integer entry_id,String type,String type_table)
    {
        String type_set=null;
        String type_map=null;
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
        type_map=type_table+"_set_map";
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
    @PostMapping("/collect_other")
    public String updateCollect_self(String open_id,String type,int set_id)
    {
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

    @PostMapping("/collect_self")
    public String update_delete_self_set(String type, int creator_id, String set_name, String creator_name, Spring private01)
    {
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
        int op=collect.insert_check_self(type_set, creator_id, set_name, creator_name, private01);
        if(op==1)
        {
            return "success";
        }
        else
        {//返回0给客户端
            return "error";
            }
    }
    @DeleteMapping("/collect_self")
    public String delete_self(int set_id,String open_id)
    {
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
}
