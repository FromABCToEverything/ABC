package com.example.demo.component;

import com.example.demo.mapper.SessionMapper;
import com.example.demo.service.impl.SessionServiceimpl;
import org.apache.commons.collections.MapIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

import static com.example.demo.DemoApplication.sessionMap;

@Component
public class ExitComponent {
    @Autowired
    //public static SqlSession sqlSession;
    public SessionServiceimpl session;
    @PreDestroy
    public void exit(){
        MapIterator map=sessionMap.mapIterator();

        String sql="insert into session (open_id,session_id) values ";
        while (map.hasNext())
        {
            String session_id= (String) map.next();
            String open_id= (String) map.getValue();
            sql+=String.format("('%s','%s'),", open_id,session_id);

        }
        //去掉末尾逗号
        sql=sql.substring(0,sql.length()-1);
        session.resetSession(sql);
        System.out.println("把数据写回session");

    }
}
