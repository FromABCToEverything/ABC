package com.example.demo.controller;

import com.example.demo.entity.GrammarEntity;
import com.example.demo.service.impl.GrammarServiceimpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GrammarController {
    @Autowired
    public GrammarServiceimpl grammar;
    @GetMapping(path ="/grammar_set")
    public Object findGrammar(String open_id, @Param("sort")String sort, @Param("order") String order)
    {
        List<GrammarEntity>list=grammar.findGrammar(open_id,sort,order);
        return list;
    }
}
