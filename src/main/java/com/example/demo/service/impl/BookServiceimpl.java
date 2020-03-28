package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceimpl implements BookService {
    @Autowired
    private BookMapper Book;
   // private Object String;

    @Override
    public List<BookEntity> showShelf(String open_id) {
        List<BookEntity> list=Book.showShelf(open_id);
        return list;
    }

    @Override
    public List<BookEntity> book_set(int set_id) {
        return Book.book_set(set_id);
    }

    @Override
    public List<BookEntity> showBook(Integer book_id) {
        List<BookEntity> list= Book.showBook(book_id);
        return list;
    }

    @Override
    public List<BookEntity> showBook_set(String open_id, @Param("sort")String sort, @Param("order") String order) {
        List<BookEntity> list=Book.showBook_set(open_id,sort,order);
        return list;
    }



    @Override
    public List<QuestionSetEntity> questionsetType(String keyword, String sort, String order) {
        if(sort.equals("t"))
            sort="last_edit_time";
        else if ((sort.equals("d")))
            sort="set_id";
        else sort="collected_times";
        return Book.questionsetType(keyword, sort, order);
    }

    @Override
    public List<WordSetEntity> wordType(String keyword, String sort, String order) {
        if(sort.equals("t"))
            sort="last_edit_time";
        else if ((sort.equals("d")))
            sort="set_id";
        else sort="collected_times";
        return Book.wordType(keyword, sort, order);
    }

    @Override
    public List<BookSetEntity> booksetType(String keyword, String sort, String order) {
        if(sort.equals("t"))
            sort="last_edit_time";
        else if ((sort.equals("d")))
            sort="set_id";
        else sort="collected_times";
        return Book.booksetType(keyword, sort, order);
    }

    @Override
    public List<BookEntity> BookType(String keyword, String sort, String order) {
        System.out.println("this is book");
        if (sort.equals('s'))
            return Book.bookType_score(keyword, sort, order);
        else {
            System.out.println("这不是按s排序");
            if (sort.equals("t"))
                sort = "last_edit_time";
            else if ((sort.equals("d"))) {
                sort = "book_id";
                System.out.println("这是按d" + sort);
            } else sort = "publicated_date";
            return Book.bookType(keyword, sort, order);
        }
    }

    @Override
    public List<NoteSetEntity> noteType(String keyword, String sort, String order) {
        if(sort.equals("t"))
            sort="last_edit_time";
        else if ((sort.equals("d")))
            sort="set_id";
        else sort="collected_times";
        return Book.noteType(keyword, sort, order);
    }

    @Override
    public List<QuestionEntity> questionType(String keyword, String sort, String order) {
        if (sort.equals('s'))
            return Book.questionType_score(keyword, sort, order);
        else {
            if(sort.equals("d"))
                sort="question_id";
            else
                sort="last_edit_time";
            return Book.questionType(keyword, sort, order);
        }
    }

    @Override
    public List<WordEntity> wordType2(String keyword, String sort, String order) {
        return Book.wordType2(keyword, sort, order);
    }


}
