package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.impl.BookServiceimpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookServiceimpl book=new BookServiceimpl();

    //前面有一些代码，通过session_id获取open_id

    @GetMapping(path ="/book_set")
    public Object showShelf(String open_id)//显示书架
    {
        List<BookEntity> list=new LinkedList<>();
        list=book.showShelf(open_id);
        return list;
    }

    @GetMapping(path = "/book_set_list")//这个是在list界面显示的东西
    public List<BookEntity> book_set(int set_id)
    {
        return book.book_set(set_id);
    }

    @GetMapping(path = "/book")
    public List<BookEntity> showBook( Integer book_id)
    {
       List<BookEntity> list= book.showBook(book_id);
       System.out.println(list.get(0).getTitle());
        return list;
    }

    @GetMapping(path="/book_set_search")
    public Object showBook_set(String open_id, @Param("sort")String sort, @Param("order") String order)
    {
        List<BookEntity> list=book.showBook_set(open_id,sort,order);
        return list;
    }

    @GetMapping(path = "/search")
    public Object showSearch(String type,String key,@Param("sort")String sort, @Param("order") String order)
    {
        String keyword="%" + key + "%";
        if(type.equals("b"))
        {
            System.out.println("book");
             return book.BookType(keyword, sort, order);
        }
        else if(type.equals("W"))
        {  return book.wordType(keyword, sort, order);
        }
        else if(type.equals("B"))
        { return book.booksetType(keyword, sort, order);
        }else if(type.equals("N"))
        { return book.noteType(keyword, sort, order);}
        else if(type.equals("Q"))
        {
            return book.questionsetType(keyword, sort, order);
        }else if(type.equals("q"))
        {
            return book.questionType(keyword, sort, order);
        }
   return 0;
    }


    @GetMapping("/search/book")
    public Object searchbook(String keyword,@Param("sort")String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
        List<BookEntity> list=book.BookType(key, sort, order);
        return list;
    }
    @GetMapping("/search/word")
    public Object searchword(String keyword,@Param("sort")String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
        List<WordSetEntity> list=book.wordType(key, sort, order);
        return list;
    }
    @GetMapping("/search/book_set")
    public Object searchbook_set(String keyword,@Param("sort")String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
        List<BookSetEntity> list=book.booksetType(key, sort, order);
        return list;
    }
    @GetMapping("/search/question")
    public Object searchquestion(String keyword,@Param("sort")String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
        List<QuestionEntity> list=book.questionType(key, sort, order);
        return list;
    }
    @GetMapping("/search/question_set")
    public Object searchQuestion(String keyword,@Param("sort")String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
        List<QuestionSetEntity> list=book.questionsetType(key, sort, order);
        return list;
    }
    @GetMapping("/search/note")
    public Object searchNote(String keyword,@Param("sort")String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
        List<NoteSetEntity> list=book.noteType(key, sort, order);
        return list;
    }

    @GetMapping(path = "/test_word")
    public Object  wordType2(String keyword,@Param("sort") String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        return book.wordType2(key, sort, order);
    }



}
