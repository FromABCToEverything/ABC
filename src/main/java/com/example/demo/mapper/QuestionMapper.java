package com.example.demo.mapper;

import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.QuestionSetEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

        @Select("select * from question where question_id=#{question_id} ")
        List<QuestionEntity>  findQusetion( int question_id );

        @Select("select * from question where question_id=" +
                     "any(select entry_id from question_set_map where set_id=" +
                         "any(select set_id from question_set where creator_id=#{open_id})) " +
                "ORDER BY #{sort} #{order} limit 20 offset 0")
        List<QuestionEntity> findQuestion_set(String open_id, @Param("sort") String sort, @Param("order") String order);

        @Update({"create TEMPORARY table IF NOT EXISTS ${table_name} like question " +
                "union "+
                "create TEMPORARY table if not exists ${table_name_open_id} like question "})
        int create_table(String table_name,String table_name_open_id);

//        @Insert({"insert into #{table_name} " +
//                "select * from question where question_id=" +
//                "(select entry_id from question_set_map where set_id=" +
//                "(select set_id from question_set where creator_id=1 AND set_name=#{book_id}))", "insert into #{table_name_open_id} " +
//                "select * from question where type='w' AND point_id=" +
//                "(select word_id from #{table_word_name}", "insert into #{table_name_open_id} select * from question"})
//        @Select("select * from question " )
//List<QuestionEntity> findQuestion_list(String table_name,String table_name_open_id,String table_word_name,String open_id, int book_id, @Param("sort") String sort, @Param("order") String order);
        @Select("select * from question where question_id= " +
                "any(select entry_id from question_set_map where set_id=" +
                "any(select set_id from question_set where creator_id=1 AND set_name=#{book_id}))" +
                " and question_id not in (select entry_id from question_set_map where set_id=" +
                "(select set_id from question_set where creator_id=#{open_id} and set_name=#{book_id}))")
        List<QuestionEntity> findQuestion_list(int book_id,String open_id);//用户登录，一本书的题目
        @Select("select * from question where question_id=" +
                "any(select entry_id from question_set_map where set_id=" +
                "any(select set_id from question_set where creator_id=1 AND set_name=#{book_id}))")
        List<QuestionEntity> findQuestion_list_un(int book_id);

        @Update("create TEMPORARY table IF NOT EXISTS ${table_name} like question " )
        void create_table_unlogin(String table_name);
//        @Insert("insert into ${table_name}  " +
//                    "(select * from question where question_id=" +
//                      "any(select entry_id from question_set_map where set_id=" +
//                       "any(select set_id from question_set where creator_id=1 AND set_name=#{book_id})))")
        @Insert("insert into ${table_name} (select * from question where question_id=" +
                " any(select entry_id from question_set_map where set_id=" +
                " any(select set_id from question_set where creator_id=1 AND set_name=#{book_id})))")
        int insertTemp(String table_name,int book_id);
        @Select("select * from ${table_name} " )
        List<QuestionEntity> findQuestion_list_unlogin(String table_name,int book_id );

        @Insert("insert into question (type,point_id,stem,choices,answer,explanation) values (#{point_id},#{stem},#{choices},#{answer},#{explanation}")
        int insert_question(String type,int point_id,String stem,String choices,String answer,String explanation);


        @Insert("insert into question_set_map (entry_id,set_id) values " +
                "((select question_id from question where point_id=#{point_id}) " +
                "," +
                "(select set_id from question_set where creator_id=#{open_id} AND set_name='默认'))")
        int insert_question_set_map(int point_id,String open_id);

        @Select("SELECT\n" +
                "\tquestion.*\n" +
                "FROM\n" +
                "\tquestion\n" +
                "\tINNER JOIN\n" +
                "\tquestion_set_map\n" +
                "\tON \n" +
                "\t\tquestion.question_id = question_set_map.entry_id\n" +
                "WHERE\n" +
                "\tquestion_set_map.set_id = #{set_id}")
        List<QuestionSetEntity> question_set(int set_id);

        @Select("select * from question where question_id=#{set_id}")
        List<QuestionEntity> question(int set_id);


}
