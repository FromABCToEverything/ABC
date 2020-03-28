package com.example.demo.service;


import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookService {

     List<BookEntity> showShelf(String open_id);
     List<BookEntity> book_set(int set_id);
     List<BookEntity> showBook(Integer book_id);

     List<BookEntity> showBook_set(String open_id, @Param("sort")String sort, @Param("order") String order);

     //List<?> showSearch(String type,String keyword,@Param("sort")String sort,@Param("order") String order);
     List<QuestionSetEntity> questionsetType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order);
     List<WordSetEntity> wordType(String keyword,@Param("sort") String sort, @Param("order") String order);
     List<BookSetEntity> booksetType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order);
     List<BookEntity> BookType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order);
     List<NoteSetEntity> noteType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order);
     List<QuestionEntity> questionType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order);

     List<WordEntity> wordType2(@Param("keyword") String keyword,@Param("sort") String sort, @Param("order") String order);
}
