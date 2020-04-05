package com.example.demo.mapper;

import com.example.demo.entity.GrammarEntity;
import com.example.demo.entity.WordEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WordMapper {

    @Select("select word.* from word where word.word_id=" +
              "any(select word_set_map.entry_id from word_set_map where word_set_map.set_id=" +
                  "any(select word_set.set_id from word_set where word_set.creator_id=#{open_id}))" +
            "limit 40 offset 0")
    List<WordEntity> findWord(String open_id);//找到用户为掌握的单词

    @Update("create temporary table  if not exists ${temp_table} like word  " )
     @Insert("insert into ${temp_table} from " +
            "(select word.* form word where word.word_id=" +
                "(select word_set_map.entry_id from word_set_map where word_set_map.set_id in " +
                 "  (select word_set.set_id as set_id1 from word_set " +
                    "where word_set.creator_id=1 and word_set.set_name=#{book_id}) " +
            "and" +
                "word_set_map.set_id not in " +
                     "(select word_set.set_id as set_id2 from word_set where creator_id=#{open_id} and set_name='默认' )) " +
            "and" +
                "word_set_map.entry_id in " +
                     "(select word_set_map.entry_id from word_set_map where word_set_map.set_id=set_id1 and word_set_map.set_id=set_id2))" )
    int update_temptable(String open_id, int book_id,String temp_table);//创建临时表


     @Select("${sql}" )
    int wordCount(String sql);//一本书中未掌握的单词数和语法数

    @Select( "select COUNT(entry_id) from ${grammar_set_temp_#{open_id}_#{book_id}}")
    int grammarCount(String open_id,int book_id);



    @Select("select COUNT(entry_id) from word_set_map where creator_id=1 " +
            "AND" +
            "set_name=#{book_id}" )
    int findCount_unlogin(int book_id);

//    @Select("select * from word where word_id=" +
//            "any(select entry_id from word_set_map where set_id=" +
//            "any(select set_id from word_set where creator_id=#{open_id} and set_name=#{book_id}))" +
//            " limit 20 offset 0" )
    @Select("${sql}")
    List<WordEntity> findWord_list(String sql);

    @Select("select * from ${grammar_temp_#{open_id}_#{book_id}} " +
            "ORDER BY ${sort} ${order} limit 20 offset 0")
    List<GrammarEntity> findGrammar_list(String open_id, int book_id, @Param("sort") String sort, @Param("order") String order);

    @Select("SELECT\n" +
            "\tword.*\n" +
            "FROM\n" +
            "\tword\n" +
            "\tINNER JOIN\n" +
            "\tword_set_map\n" +
            "\tON \n" +
            "\t\tword.word_id = word_set_map.entry_id\n" +
            "\tINNER JOIN\n" +
            "\tword_set\n" +
            "\tON \n" +
            "\t\tword_set_map.set_id = word_set.set_id\n" +
            "WHERE\n" +
            "\tword_set.creator_id = 1 AND\n" +
            "\tword_set.set_name = #{book_id}"+
            " limit 20 offset 0")
    List<WordEntity> findWord_list_unlogin(int book_id);


    @Insert("select * form grammar where word_id=" +
            "(select entry_id from grammar_set_map where set_id in " +
            "  (select set_id  from word_set " +
            "where creator_id=1 AND set_name=#{book_id})) " +
            "ORDER BY ${sort} ${order} limit 20 offset 0")
    List<GrammarEntity> findGrammar_list_unlogin(int book_id,@Param("sort")String sort, @Param("order") String order);
    @Select("select * from word where word_id=#{word_id}")
    List<WordEntity> word(int word_id);


    @Select("SELECT\n" +
            "\tword.*\n" +
            "FROM\n" +
            "\tword\n" +
            "\tINNER JOIN\n" +
            "\tword_set_map\n" +
            "\tON \n" +
            "\t\tword.word_id = word_set_map.entry_id\n" +
            "\tINNER JOIN\n" +
            "\tword_set\n" +
            "\tON \n" +
            "\t\tword_set_map.set_id = word_set.set_id\n" +
            "WHERE\n" +
            "\tword_set.creator_id = 1 AND\n" +
            "\tword_set.set_name = #{book_id} " +
            "order by word_id DESC " +
            "limit 20 offset #{index}")
    List<WordEntity> findWord_in_book(int book_id,int index);

    @Select("SELECT\n" +
            "\tword.*\n" +
            "FROM\n" +
            "\tword\n" +
            "\tINNER JOIN\n" +
            "\tword_set_map\n" +
            "\tON \n" +
            "\t\tword.word_id = word_set_map.entry_id\n" +
            "WHERE\n" +
            "\tword_set_map.set_id = #{set_id} " +
            "limit 20 offset #{index}")
    List<WordEntity> word_set(int set_id,int index);



}
