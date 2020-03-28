package com.example.demo.service;

import com.example.demo.entity.GrammarEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GrammarService {
    List<GrammarEntity> findGrammar(String open_id, @Param("sort")String sort, @Param("order") String order);
}
