package com.example.demo.service;


import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;


public interface BookService {

     Map<String, Object> showShelf(String open_id,int index);
     List<BookEntity> book_set(int set_id,int index);
     List<BookEntity> showBook(Integer book_id);

     List<BookEntity> showBook_set(String open_id, @Param("sort")String sort, @Param("order") String order);

     //List<?> showSearch(String type,String keyword,@Param("sort")String sort,@Param("order") String order);
     List<QuestionSetEntity> questionsetType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);
     List<WordSetEntity> wordType(String keyword,@Param("sort") String sort, @Param("order") String order,int startIndex);
     List<BookSetEntity> booksetType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);
     List<BookEntity> BookType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);
     List<NoteSetEntity> noteType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);
     List<QuestionEntity> questionType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

     List<WordEntity> wordType2(@Param("keyword") String keyword,@Param("sort") String sort, @Param("order") String order);

     List<BookEntity> test_map(String sql);
}
