package com.example.demo.mapper;

import com.example.demo.entity.SessionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SessionMapper {
    @Select("select * from session")
    List<SessionEntity> getUser();

    @Insert("insert into session (session_id,open_id) values (#{session_id},#{open_id}")
    void resetSession(String session_id,String open_id);

    //void resetSession(Object key, Object value);
}
