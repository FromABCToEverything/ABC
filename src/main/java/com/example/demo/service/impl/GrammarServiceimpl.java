package com.example.demo.service.impl;

import com.example.demo.entity.GrammarEntity;
import com.example.demo.mapper.GrammarMapper;
import com.example.demo.service.GrammarService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrammarServiceimpl implements GrammarService {
    @Autowired
    private GrammarMapper grammar;
    @Override
    public List<GrammarEntity> findGrammar(String open_id, @Param("sort")String sort, @Param("order") String order) {
        List<GrammarEntity> list=grammar.findGrammar(open_id,sort,order);
        return list;
    }
}
