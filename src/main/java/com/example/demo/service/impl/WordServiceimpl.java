package com.example.demo.service.impl;

import com.example.demo.entity.WordEntity;
import com.example.demo.mapper.WordMapper;
import com.example.demo.service.WordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WordServiceimpl implements WordService {
    @Autowired
    private WordMapper word;

    @Override
    public List<WordEntity> findWord(String open_id) {
        List<WordEntity> list=word.findWord(open_id);
        return list;
    }

    @Override
    public Map<String,Integer> findCount(String open_id, int book_id) {
        String table_name="words_temp_"+open_id+"_"+book_id;
        word.update_temptable(open_id, book_id,table_name);
        Map<String,Integer> wordmap = null;
        wordmap.put("word",word.wordCount(open_id, book_id,table_name));
        //wordmap.put("grammar",word.grammarCount(open_id, book_id,table_name));
        return wordmap;
    }//返回一本书中未掌握单词数和语法数

    @Override
    public Map<String,Integer> findCount_unlogin(int book_id) {
        Map<String,Integer> map=null;
        map.put("word",word.findCount_unlogin(book_id));
        return map;
    }

    @Override
    public List<WordEntity> finW_list(String open_id, int book_id) {
        //word.findGrammar_list(open_id,book_id,sort,order);

        System.out.println("这是登陆的word");
        return word.findWord_list(open_id,book_id);
    }

    @Override
    public List<WordEntity> findW_list_unlogin(int book_id) {
        System.out.println("这是没登录的word");
       return word.findWord_list_unlogin(book_id);

    }

    @Override
    public int Count(String open_id, int book_id) {
        String table_name="words_temp_"+open_id+"_"+book_id;
        word.update_temptable(open_id, book_id,table_name);

        return word.wordCount(open_id, book_id,table_name);
        //wordmap.put("grammar",word.grammarCount(open_id, book_id,table_name));

    }//返回一本书中未掌握单词数和语法数

    @Override
    public List<WordEntity> word(int word_id) {
        return word.word(word_id);
    }

    @Override
    public List<WordEntity> findWord_in_book(int book_id) {
        return word.findWord_in_book(book_id);
    }

    @Override
    public List<WordEntity> word_set(int set_id) {
        return word.word_set(set_id);
    }
}
