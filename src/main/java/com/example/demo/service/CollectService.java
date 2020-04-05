package com.example.demo.service;

import com.example.demo.dto.myBookSetDto;
import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.NoteSetEntity;
import com.example.demo.entity.QuestionSetEntity;
import com.example.demo.entity.WordSetEntity;
import org.apache.ibatis.annotations.Param;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface CollectService {

    Object showCollect_other(String open_id,String type,String type_set,int page);
    List<BookSetEntity> showCollect_selfB(String open_id,int index);
    List<WordSetEntity> showCollect_selfW(String open_id,int index);
    List<QuestionSetEntity> showCollect_selfQ(String open_id,int index);
    List<NoteSetEntity> showCollect_selfN(String open_id,int index);
    int insert_check(String type_map,int set_id,int entry_id);
    void update_set(@Param("type_set")String type_set, @Param("set_id")int set_id, @Param("open_id")String open_id);
    int insert_check2(String open_id,String type,int set_id);
    void update_set_createdtime(@Param("type_set")String type_set,@Param("set_id")int set_id,@Param("open_id")String open_id);
    int insert_check_self(String type_set, String creator_id, String set_name, String creator_name, Spring private01);
    int delete_self(int set_id,String open_id);

    List<myBookSetDto> collect_element(String type,int entry_id, String open_id);
    String if_collect_other(String type,int entry_id,String open_id);

}
