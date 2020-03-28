package com.example.demo.service;

import com.example.demo.entity.WordEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface WordService {
    List<WordEntity> findWord(String open_id);
    Map<String,Integer> findCount(String open_id, int book_id);
    Map<String,Integer> findCount_unlogin(int book_id);
    List<WordEntity> finW_list(String open_id, int book_id);
    List<WordEntity> findW_list_unlogin(int book_id);
    int Count(String open_id, int book_id);
    List<WordEntity> word(int word_id);
    List<WordEntity> findWord_in_book(int book_id);
    List<WordEntity> word_set(int set_id);
}
