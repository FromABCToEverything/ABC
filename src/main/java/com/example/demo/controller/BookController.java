package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.impl.BookServiceimpl;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Book;
import java.util.*;

import static com.example.demo.DemoApplication.sessionMap;

@RestController
public class BookController {
    @Autowired
    private BookServiceimpl book=new BookServiceimpl();

    //前面有一些代码，通过session_id获取open_id

    @GetMapping(path ="/book_set")
    public Map<String, Object> showShelf(String session_id,int page)//显示书架
    {
//        int pageSize = 20;//默认每页4条消息
//        int index=(page-1)*pageSize;//现在的起始位置
        String open_id= (String) sessionMap.get(session_id);
        return book.showShelf(open_id,page);
    }

    @GetMapping(path = "/book_set_list")//这个是在list界面显示的东西
    public List<BookEntity> book_set(int set_id,int page)
    {
//        int pageSize=20;
//        int index=(page-1)*pageSize;
        return book.book_set(set_id,page);
    }

    @GetMapping(path = "/book")
    public List<BookEntity> showBook( Integer book_id)
    {
       List<BookEntity> list= book.showBook(book_id);
       System.out.println(list.get(0).getTitle());
        return list;
    }

    @GetMapping(path="/book_set_search")
    public Object showBook_set(String session_id, @Param("sort")String sort, @Param("order") String order)
    {
        String open_id= (String) sessionMap.get(session_id);
        List<BookEntity> list=book.showBook_set(open_id,sort,order);
        return list;
    }

    @GetMapping(path = "/search")
    public Object showSearch(String type,String key,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String keyword="%" + key + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页4条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        if(type.equals("b"))
        {
            System.out.println("book");
             return book.BookType(keyword, sort, order,currentPage);
        }
        else if(type.equals("W"))
        {  return book.wordType(keyword, sort, order,currentPage);
        }
        else if(type.equals("B"))
        { return book.booksetType(keyword, sort, order,currentPage);
        }else if(type.equals("N"))
        { return book.noteType(keyword, sort, order,currentPage);}
        else if(type.equals("Q"))
        {
            return book.questionsetType(keyword, sort, order,currentPage);
        }else if(type.equals("q"))
        {
            return book.questionType(keyword, sort, order,currentPage);
        }
   return 0;
    }


    @GetMapping("/search/book")
    public Object searchbook(String keyword,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页4条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        List<BookEntity> list=book.BookType(key, sort, order,currentPage);
        return list;
    }
    @GetMapping("/search/word")
    public Object searchword(String keyword,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页20条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        return book.wordType(key, sort, order,currentPage);

    }
    @GetMapping("/search/book_set")
    public Object searchbook_set(String keyword,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页20条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        List<BookSetEntity> list=book.booksetType(key, sort, order,currentPage);
        return list;
    }
    @GetMapping("/search/question")
    public Object searchquestion(String keyword,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页20条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        List<QuestionEntity> list=book.questionType(key, sort, order,currentPage);
        return list;
    }
    @GetMapping("/search/question_set")
    public Object searchQuestion(String keyword,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页20条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        List<QuestionSetEntity> list=book.questionsetType(key, sort, order,currentPage);
        return list;
    }
    @GetMapping("/search/note")
    public Object searchNote(String keyword,@Param("sort")String sort, @Param("order") String order,Integer currentPage)
    {
        String key="%" + keyword + "%";
        if(order.equals("0"))
            order="DESC";
        else order="ASC";
//        int pageSize = 20;//默认每页20条消息
//        int startIndex=(currentPage-1)*pageSize;//现在的起始位置
        List<NoteSetEntity> list=book.noteType(key, sort, order,currentPage);
        return list;
    }

    @GetMapping(path = "/test_word")
    public Object  wordType2(String keyword,@Param("sort") String sort, @Param("order") String order)
    {
        String key="%" + keyword + "%";
        return book.wordType2(key, sort, order);
    }

    @GetMapping(path = "/map")
    public List<BookEntity> test_map()
    {
        String sql="select * from book where book_id<10";
        return book.test_map(sql);
    }



}
