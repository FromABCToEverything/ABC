package com.example.demo.mapper;

import com.example.demo.entity.CommentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT " +
            "`comment`.* " +
            "FROM" +
            "`comment` " +
            "WHERE " +
            "`comment`.type = #{type} AND " +
            "`comment`.to_id = #{to_id} " +
            "ORDER BY " +
            "`comment`.last_edit_time DESC " +
            "limit 20 offset #{page}")
    List<CommentEntity>search_comment(String type, int to_id,int page);

    @Insert("insert ignore into comment (type,to_id,commentator_id) values (#{type},#{to_id},#{open_id}) " )
    int insertComment_ifnotexist(String type,int to_id,String open_id);//如果返回0，表示已经存在，执行语句二

    @Update("INSERT INTO score (type,id) VALUES(#{type},#{to_id}) " +
            "  ON DUPLICATE KEY " +
            "update " +
            "total_score=total_score+#{score} , scored_times=scored_times+1 " +
            "AND avg_score=total_score/(scored_times+1) " )
    int update_comment_noexist(String type,int to_id,int score);//上一行创建成功，执行此函数

    @Update("select score as pre_score from comment where type=#{type} AND to_id=#{to_id} AND commentator_id=#{open_id} " +
            " union " +
            "update comment set content=#{content},score=#{score} where " +
            "type=#{type} and to_id=#{to_id} AND commentator_id=#{open_id} " +
            "union " +
            "update (select * from score where type=#{type} AND id=#{to_id}) " +
            "set total_score=total_score-pre_score+#{score},avg_score=total_score/(scored_times+1)")
    int update_comment_exist(String type,int to_id,String open_id,int score,String content);//一开始就存在此评论，则更新



}
