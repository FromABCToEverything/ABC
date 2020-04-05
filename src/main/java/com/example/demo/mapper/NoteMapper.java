package com.example.demo.mapper;

import com.example.demo.entity.NoteEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT\n" +
            "\tnote.*\n" +
            "FROM\n" +
            "\tnote\n" +
            "\tINNER JOIN\n" +
            "\tnote_set_map\n" +
            "\tON \n" +
            "\t\tnote.note_id = note_set_map.entry_id\n" +
            "WHERE\n" +
            "\tnote_set_map.set_id = #{set_id} " +
            "limit 20 offset #{index}")
    List<NoteEntity> findNote(int set_id,int index);
    //要加一个post 笔记的
    //@Insert("insert ")
    @Insert("insert into note (creator_id,creator,avatar_url,title,content) values (1,'默认',2,#{title},#{content})")
    int insertNote(String title,String content);
}
