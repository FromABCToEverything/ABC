package com.example.demo.mapper;

import com.example.demo.dto.myBookSetDto;
import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.NoteSetEntity;
import com.example.demo.entity.QuestionSetEntity;
import com.example.demo.entity.WordSetEntity;
//import com.example.demo.entity.myBookSetEntity;
import org.apache.ibatis.annotations.*;

import javax.swing.*;
import java.util.List;

@Mapper
public interface CollectMapper {
    @Select("select * from ${type_set} where set_id=" +
            "any(select entry_id from collect where collector_id=#{open_id} and type=#{type}) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 20 offset #{page}")
    List<WordSetEntity> showCollect_otherW(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, int page);

    @Select("select * from ${type_set} where set_id=" +
            "any(select entry_id from collect where collector_id=#{open_id} and type=#{type}) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 20 offset #{page}")
    List<BookSetEntity> showCollect_otherB(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, int page);
    @Select("select * from ${type_set} where set_id=" +
            "any(select entry_id from collect where collector_id=#{open_id} and type=#{type}) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 20 offset #{page}")
    List<QuestionSetEntity> showCollect_otherQ(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set,int page);
    @Select("select * from ${type_set} where set_id=" +
            "any(select entry_id from collect where collector_id=#{open_id} and type=#{type}) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 20 offset #{page}")
    List<NoteSetEntity> showCollect_otherN(@Param("open_id") String open_id, @Param("type")String type, @Param("type_set")String type_set, int page);
    @Select("select * from ${type_set} where set_id=" +
            "any(select ${type_map}.set_id from ${type_map} where ${type_map}.entry_id=" +
            "any(select entry_id from collect where collector_id=#{open_id} and type=#{type})) " +
            "ORDER BY last_edit_time DESC " +
            "LIMIT 20 offset #{page}")
    Object showCollect_other(@Param("open_id") String open_id,@Param("type")String type,@Param("type_set")String type_set,int page);


    @Select("select * from word_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT #{index}, 20")
    List<WordSetEntity> showCollect_selfW(@Param("open_id")String open_id,int index);

    @Select("select * from book_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT #{index}, 20")
    List<BookSetEntity> showCollect_selfB(@Param("open_id")String open_id,int index);
    @Select("select * from question_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT #{index}, 20")
    List<QuestionSetEntity> showCollect_selfQ(@Param("open_id")String open_id,int index);
    @Select("select * from note_set where creator_id=#{open_id} ORDER BY last_edit_time DESC LIMIT #{index}, 20")
    List<NoteSetEntity> showCollect_selfN(@Param("open_id")String open_id,int index);

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
    int insert_check_self(String type_set, String creator_id, String set_name, String creator_name, Spring private01);

    @Delete("delete from ${type_set} where set_id=#{set_id} AND creator_id=#{open_id]")
    int delete_self(int set_id,String open_id);

    @Select("(select book_set.set_id as set_id,\n" +
            "\tbook_set.set_name as set_name,\n" +
            "\ttrue as collected\n" +
            "\tfrom book_set\n" +
            "\twhere book_set.creator_id =#{open_id} \n" +
            "\tand book_set.set_id in ((SELECT\n" +
            "\t\tbook_set_map.set_id\n" +
            "\tFROM\n" +
            "\t\tbook_set_map\n" +
            "\tWHERE\n" +
            "\t\tbook_set_map.entry_id = #{entry_id} )) )\n" +
            "union \n" +
            "(select book_set.set_id as set_id,\n" +
            "\tbook_set.set_name as set_name,\n" +
            "\tfalse as collected\n" +
            "\tfrom book_set\n" +
            "\twhere book_set.creator_id = #{open_id} \n" +
            "\tand book_set.set_id not in ((SELECT\n" +
            "\t\tbook_set_map.set_id\n" +
            "\tFROM\n" +
            "\t\tbook_set_map\n" +
            "\tWHERE\n" +
            "\t\tbook_set_map.entry_id = #{entry_id})))")
    List<myBookSetDto> collect_book(int entry_id, String open_id);

    @Select("(select word_set.set_id as set_id,\n" +
            "\tword_set.set_name as set_name,\n" +
            "\ttrue as collected\n" +
            "\tfrom word_set\n" +
            "\twhere word_set.creator_id =#{open_id} \n" +
            "\tand word_set.set_id in ((SELECT\n" +
            "\t\tword_set_map.set_id\n" +
            "\tFROM\n" +
            "\t\tword_set_map\n" +
            "\tWHERE\n" +
            "\t\tword_set_map.entry_id = #{entry_id} )) )\n" +
            "union \n" +
            "(select word_set.set_id as set_id,\n" +
            "\tword_set.set_name as set_name,\n" +
            "\tfalse as collected\n" +
            "\tfrom word_set\n" +
            "\twhere word_set.creator_id = #{open_id} \n" +
            "\tand word_set.set_id not in ((SELECT\n" +
            "\t\tword_set_map.set_id\n" +
            "\tFROM\n" +
            "\t\tword_set_map\n" +
            "\tWHERE\n" +
            "\t\tword_set_map.entry_id = #{entry_id})))")
    List<myBookSetDto> collect_word(int entry_id, String open_id);

    @Select("(select question_set.set_id as set_id,\n" +
            "\tquestion_set.set_name as set_name,\n" +
            "\ttrue as collected\n" +
            "\tfrom question_set\n" +
            "\twhere question_set.creator_id =#{open_id} \n" +
            "\tand question_set.set_id in ((SELECT\n" +
            "\t\tquestion_set_map.set_id\n" +
            "\tFROM\n" +
            "\t\tquestion_set_map\n" +
            "\tWHERE\n" +
            "\t\tquestion_set_map.entry_id = #{entry_id} )) )\n" +
            "union \n" +
            "(select question_set.set_id as set_id,\n" +
            "\tquestion_set.set_name as set_name,\n" +
            "\tfalse as collected\n" +
            "\tfrom question_set\n" +
            "\twhere question_set.creator_id = #{open_id} \n" +
            "\tand question_set.set_id not in ((SELECT\n" +
            "\t\tquestion_set_map.set_id\n" +
            "\tFROM\n" +
            "\t\tquestion_set_map\n" +
            "\tWHERE\n" +
            "\t\tquestion_set_map.entry_id = #{entry_id})))")
    List<myBookSetDto> collect_question(int entry_id, String open_id);

    @Select("select true as collected from collect where " +
            "type=#{type} , entry_id=#{entry_id} and creator_id=#{open_id} " +
            "union " +
            "select false as collected from collect where " +
            "type=#{type} and entry_id=#{entry_id} and collector_id=#{open_id}")
    String if_collect_other(String type,int entry_id,String open_id);



}
