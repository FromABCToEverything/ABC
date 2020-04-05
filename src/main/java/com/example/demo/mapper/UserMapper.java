package com.example.demo.mapper;


import com.example.demo.entity.SessionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (open_id,avatar_url,name,reputation) values " +
            "(#{openid},#{avatar_url},#{name},0)")
    int insertUser(String openid,String avatar_url,String name);



}
