package com.example.demo.mapper;

import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {

//    @Select("select * from book where book_id=" +
//            "(select entry_id from book_set_map where set_id=" +
//            "(select set_id from book_set where set_name='默认' AND creator_id=#{open_id}))" +
//            "limit 20 offset 0")
    @Select("SELECT book.*" +
            "FROM " +
            "book " +
            "INNER JOIN " +
            "book_set_map " +
            "ON " +
            "book.book_id = book_set_map.entry_id " +
            "INNER JOIN " +
            "book_set " +
            "ON " +
            "book_set_map.set_id = book_set.set_id " +
           " WHERE "+
           " book_set.creator_id = #{open_id} AND "+
           " book_set.set_name = '默认' "+
           " LIMIT #{index} ,20")
    List<BookEntity> showShelf(@Param("open_id") String open_id,int index) ;

    @Select("select set_id from book_set where creator_id=#{open_id}")
    Integer ShelfSet(String open_id);



    @Select("SELECT\n" +
            "\tbook.*\n" +
            "FROM\n" +
            "\tbook\n" +
            "\tINNER JOIN\n" +
            "\tbook_set_map\n" +
            "\tON \n" +
            "\t\tbook.book_id = book_set_map.entry_id\n" +
            "WHERE\n" +
            "\tbook_set_map.set_id = #{set_id} " +
            "limit #{index},20")
    List<BookEntity> book_set(int set_id,int index);//这是在list界面

   /* @Select("select * from book where book_id=#{book_id} limit 20 offset 0 " +
             "union " +
            "select book_set_map.set_id from book_set_map where book_set_map.entry_id=#{book_id} ")*/
   @Select("select * from book where book_id=#{book_id} limit 20 offset 0")
    List<BookEntity> showBook(Integer book_id);

//    @Select("select * from book where book_id=" +
//            "(select entry_id from book_set_map where set_id=" +
//            "(select set_id from book_set where creator_id=#{open_id}))" +
//            "ORDER BY #{sort} #{order} limit 20 offset 0")
    @Select("SELECT book.* " +
        "FROM " +
        "book " +
        "INNER JOIN " +
        "book_set_map " +
        "ON " +
        "book.book_id = book_set_map.entry_id " +
        "INNER JOIN " +
        "book_set " +
        "ON " +
        "book_set_map.set_id = book_set.set_id " +
        "WHERE "+
        "book_set.creator_id = #{open_id} "+
        "ORDER BY ${sort} ${order} limit 20 offset 0")
    List<BookEntity> showBook_set(String open_id, @Param("sort") String sort, @Param("order") String order);

//    @Select("select * from #{type} where book.title|book.author like '%${keyword}%' or " +
//            "word.content like '%${keyword}%' or grammar.description like '%${keyword}%' or " +
//            "question.stem like '%${keyword}%' or note.title|note.content like '%${keyword}%' " +
//            "or book_set.set_name like '%${keyword}%' or " +
//            "question_set.set_name like '%${keyword}%' " +
//            "ORDER BY #{sort} #{order} limit 20 offset 0 " )
//   List<BookEntity> showSearch(@Param("type") String type, @Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order);

   @Select("select * from book where book.title like (#{keyword}) or book.author like (#{keyword})" +
           " ORDER BY ${sort} ${order}  ")
   List<BookEntity> bookType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

   //这是按照评分排序limit #{startIndex},20
    @Select("SELECT\n" +
            "\tbook.*\n" +
            "FROM\n" +
            "\tbook\n" +
            "\tINNER JOIN\n" +
            "\tscore\n" +
            "\tON \n" +
            "\t\tbook.book_id = score.id\n" +
            "WHERE\n" +
            "\tbook.title like #{keyword}\n" +
            " AND\n" +
            "\tbook.author like #{keyword}\n" +
            "AND\n" +
            "score.type=b" +
            "ORDER BY\n" +
            "\tscore.avg_score ${order} " +
            "limit 20 off #{startIndex}")
    List<BookEntity> bookType_score(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

   @Select("select * from word_set where set_name like #{keyword} " +
           "ORDER BY ${sort} ${order} limit 20 offset #{startIndex} ")
    List<WordSetEntity> wordType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

   @Select("select * from question where question.stem like #{keyword} " +
           "ORDER BY last_edit_time ${order} " +
           "limit 20 offset #{startIndex} ")
   List<QuestionEntity> questionType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

   @Select("SELECT\n" +
           "\tquestion.*\n" +
           "FROM\n" +
           "\tquestion\n" +
           "\tINNER JOIN\n" +
           "\tscore\n" +
           "\tON \n" +
           "\t\tquestion.question_id = score.id\n" +
           "WHERE\n" +
           "\tquestion.stem like #{keyword}\n" +
           " AND\n" +
           "\tscore.type = q\n" +
           "ORDER BY\n" +
           "\tscore.avg_score ${order} " +
           "limit 20 offset #{startIndex}")
   List<QuestionEntity> questionType_score(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

   @Select("select * from note_set where set_name like #{keyword} " +
           "ORDER BY ${sort} ${order} limit 20 offset #{startIndex}")
    List<NoteSetEntity> noteType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

    @Select("select * from book_set where set_name like #{keyword} "+
            "ORDER BY ${sort} ${order} limit 20 offset #{startIndex}")
    List<BookSetEntity> booksetType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);

    @Select("select * from question_set where set_name like #{keyword} "+
            "ORDER BY ${sort} ${order} limit 20 offset #{startIndex}")
    List<QuestionSetEntity> questionsetType(@Param("keyword") String keyword, @Param("sort") String sort, @Param("order") String order,int startIndex);


    @Select("select * from word where word.content like #{keyword} " +
            "ORDER BY ${sort} DESC limit 20 offset 0 " )
    List<WordEntity> wordType2(@Param("keyword") String keyword,@Param("sort") String sort, @Param("order") String order);


    @Select("${sql}")
    List<BookEntity> test_map(String sql);
}
