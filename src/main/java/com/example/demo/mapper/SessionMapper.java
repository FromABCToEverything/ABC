package com.example.demo.mapper;

import com.example.demo.entity.BookSetEntity;
import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.WordSetEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SessionMapper {
    @Select("select * from session")
    List<SessionEntity> getUser();
    @Update("truncate table session")
    void droptable();
    //@Delete("delete from session")
    //@Update("drop table session;create table session ( `open_id` varchar(255) ,`session_id` varchar(255))")
    @Insert("${sql}")
    void resetSession(String sql);

    @Select("select open_id from session where session_id=#{session_id}")
    String findOpen_id(String session_id);

    @Select("${sql}")
    int add_vocabulary(String sql);

    @Update("${sql}")
    int add_process(String sql);

    @Update("${sql}")
    int add_plan(String sql);

//    @Select("SELECT" +
//            "    word_set.*" +
//            "    FROM" +
//            "    word_set" +
//            "    INNER JOIN" +
//            "    plan" +
//            "    ON " +
//            "    word_set.set_id = plan.set_id" +
//            "    WHERE" +
//            "    plan.open_id =#{open_id}")
    @Select("${sql}")
    List<WordSetEntity> select_plan(String sql);

    @Select("${sql}")
    List<Map<String,Object>> select_1(String sql);


}
