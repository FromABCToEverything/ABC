package com.example.demo.mapper;

import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.NoteSetEntity;
import com.example.demo.entity.QuestionSetEntity;
import com.example.demo.entity.WordSetEntity;
import org.apache.ibatis.annotations.*;

import javax.swing.*;
import java.util.List;

@Mapper
public interface CollectMapper {
    @Select("select * from ${type_set} where set_id=" +
            "(select ${type_map}.set_id from ${type_map} where ${type_map}.entry_id=" +
            "(select entry_id from collect where collector_id=#{open_id} and type=#{type})) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 0, 20")
    List<WordSetEntity> showCollect_otherW(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, @Param("type_map")String type_map);

    @Select("select * from ${type_set} where set_id=" +
            "(select ${type_map}.set_id from ${type_map} where ${type_map}.entry_id=" +
            "(select entry_id from collect where collector_id=#{open_id} and type=#{type})) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 0, 20")
    List<BookSetEntity> showCollect_otherB(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, @Param("type_map")String type_map);
    @Select("select * from ${type_set} where set_id=" +
            "(select ${type_map}.set_id from ${type_map} where ${type_map}.entry_id=" +
            "(select entry_id from collect where collector_id=#{open_id} and type=#{type})) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 0, 20")
    List<QuestionSetEntity> showCollect_otherQ(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, @Param("type_map")String type_map);
    @Select("select * from ${type_set} where set_id=" +
            "(select ${type_map}.set_id from ${type_map} where ${type_map}.entry_id=" +
            "(select entry_id from collect where collector_id=#{open_id} and type=#{type})) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 0, 20")
    List<NoteSetEntity> showCollect_otherN(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, @Param("type_map")String type_map);
    @Select("select * from ${type_set} where set_id=" +
            "(select ${type_map}.set_id from ${type_map} where ${type_map}.entry_id=" +
            "(select entry_id from collect where collector_id=#{open_id} and type=#{type})) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 0, 20")
    Object showCollect_other(@Param("open_id") String open_id,@Param("type")String type,@Param("type_set")String type_set,@Param("type_map")String type_map);


    @Select("select * from word_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT 0, 20")
    List<WordSetEntity> showCollect_selfW(@Param("open_id")String open_id);

    @Select("select * from book_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT 0, 20")
    List<BookSetEntity> showCollect_selfB(@Param("open_id")String open_id);
    @Select("select * from question_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT 0, 20")
    List<QuestionSetEntity> showCollect_selfQ(@Param("open_id")String open_id);
    @Select("select * from note_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT 0, 20")
    List<NoteSetEntity> showCollect_selfN(@Param("open_id")String open_id);

    @Insert("insert ignore into ${type_map} (set_id,entry_id) values (#{set_id},#{entry_id})")
    int insert_check(String type_map,int set_id,int entry_id);

    @Update("Update ${type_set} set `last_edit_time`=now() " +
            "where set_id=#{set_id} AND creator_id=#{open_id}")
    void update_set(@Param("type_set")String type_set,@Param("set_id")int set_id,@Param("open_id")String open_id);

    @Insert("insert into collect (collector_id,type,entry_id) values (#{open_id},#{type},#{set_id})")
   // @SelectKey(statement="check insert", keyProperty="collector_id", before=false, resultType=int.class)
    int insert_check2(String open_id,String type,int set_id);

    @Update("Update ${type_set} set created_time=created_time+1 " +
            "where set_id=#{set_id} AND creator_id=#{open_id}")
    void update_set_ctratedtime(@Param("type_set")String type_set,@Param("set_id")int set_id,@Param("open_id")String open_id);

    @Insert("insert into ${type_set} (creator_id,set_name,creator_name,private,created_time,last_edit_time) " +
            "values (#{creator_id},#{set_name},#{creator_name},#{private01},NOW(),NOW())")
    //@SelectKey(statement="check insert_self", keyProperty="", before=false, resultType=int.class)
    int insert_check_self(String type_set, int creator_id, String set_name, String creator_name, Spring private01);

    @Delete("delete from ${type_set} where set_id=#{set_id} AND creator_id=#{open_id]")
    int delete_self(int set_id,String open_id);



}
