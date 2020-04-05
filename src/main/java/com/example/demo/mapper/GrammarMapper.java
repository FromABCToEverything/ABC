package com.example.demo.mapper;

import com.example.demo.entity.GrammarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GrammarMapper {

    @Select("select * from question where question_id=" +
            "(select entry_id from question_set_map where set_id=" +
            "(select set_id from question_set where creator_id=#{open_id})) " +
            "ORDER BY ${sort} ${order} limit 20 offset 0")
    List<GrammarEntity> findGrammar(String open_id, @Param("sort") String sort, @Param("order") String order);
}
