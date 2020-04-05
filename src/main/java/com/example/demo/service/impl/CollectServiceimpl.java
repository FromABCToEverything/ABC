package com.example.demo.service.impl;

import com.example.demo.dto.myBookSetDto;
import com.example.demo.entity.*;
import com.example.demo.mapper.CollectMapper;
import com.example.demo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceimpl implements CollectService {
    @Autowired
    private CollectMapper collect;
    @Override
    public Object showCollect_other(String open_id, String type,String type_set, int page) {
        if(type.equals("W"))
            return collect.showCollect_otherW(open_id,type,type_set,page);
        else if(type.equals("B"))
            return collect.showCollect_otherB(open_id,type,type_set,page);
       else if(type.equals("Q"))
            return collect.showCollect_otherQ(open_id,type,type_set,page);
        else if(type.equals("N"))
            return collect.showCollect_otherN(open_id,type,type_set,page);
        return 0;
    }

    @Override
    public List<BookSetEntity> showCollect_selfB(String open_id,int index) {
        return collect.showCollect_selfB(open_id,index);
    }

    @Override
    public List<WordSetEntity> showCollect_selfW(String open_id,int index) {
        return collect.showCollect_selfW(open_id,index);
    }

    @Override
    public List<QuestionSetEntity> showCollect_selfQ(String open_id,int index) {
        return collect.showCollect_selfQ(open_id,index);
    }

    @Override
    public List<NoteSetEntity> showCollect_selfN(String open_id,int index) {
        return collect.showCollect_selfN(open_id,index);
    }


    @Override
    public int insert_check(String type_map, int set_id, int entry_id) {
        collect.insert_check(type_map, set_id, entry_id);
//        if(op>0)
//            op=1;
//        else
//            op=0;
        return 1;
    }

    @Override
    public void update_set(String type_set, int set_id, String open_id) {
        collect.update_set(type_set, set_id, open_id);
    }

    @Override
    public int insert_check2(String open_id, String type, int set_id) {
        int op=collect.insert_check2(open_id, type, set_id);
        if(op!=0)
            op=1;
        else
            op=0;
        return op;
    }

    @Override
    public void update_set_createdtime(String type_set, int set_id, String open_id) {
        collect.update_set_ctratedtime(type_set, set_id, open_id);
    }

    @Override
    public int insert_check_self(String type_set, String creator_id, String set_name, String creator_name, Spring private01) {
        int op=collect.insert_check_self(type_set, creator_id, set_name, creator_name, private01);
        if(op!=0)
            op=1;
        else
            op=0;
        return op;
    }

    @Override
    public int delete_self(int set_id, String open_id) {
        int op=collect.delete_self(set_id, open_id);
        if(op!=0)
            op=1;
        else
            op=0;
        return op;
    }

    @Override
    public List<myBookSetDto> collect_element(String type,int entry_id, String open_id) {
        List<myBookSetDto> list = null;
        if(type.equals("b"))
            list=collect.collect_book(entry_id, open_id);
        else if(type.equals("w"))
            list=collect.collect_word(entry_id, open_id);
        else if(type.equals("q"))
           list=collect.collect_question(entry_id, open_id);
        return list;
    }

    @Override
    public String if_collect_other(String type, int entry_id, String open_id) {
        return collect.if_collect_other(type, entry_id, open_id);
    }
}
