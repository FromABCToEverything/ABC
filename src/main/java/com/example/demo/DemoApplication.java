package com.example.demo;

//import com.example.demo.config.SpringUtil;
import com.example.demo.component.ExitComponent;
import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.SessionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.impl.SessionServiceimpl;
import com.example.demo.service.impl.UserServiceimpl;
import com.mysql.cj.Session;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
@SpringBootApplication()
public class DemoApplication {
    //public static UserServiceimpl user ;
    @Autowired
    //public static SqlSession sqlSession;
    public SessionMapper sessionMapper;
    public ExitComponent exit;
    public static DualHashBidiMap sessionMap = new DualHashBidiMap();
    public static DemoApplication demo;
    @PostConstruct
    public void init() {
        demo = this;
        demo.sessionMapper = this.sessionMapper;
    }

    //    static {
    //待实现，将session表里记录读出，填到sessionMap表里
    // list=user.getUser();
    // user= SpringUtil.getBean(UserServiceimpl.class);
    //user=sqlSession.getMapper(SessionMapper.class);
    public static void getUser() {
        List<SessionEntity> list = demo.sessionMapper.getUser();
        for (SessionEntity User : list) {
            sessionMap.put(User.getSessionId(), User.getOpenId());
        }
        System.out.println(list.get(1).getOpenId());
        System.out.println("这是在设置map中数据");
    }
//    }
    @PreDestroy
    public static void exit(){
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
        demo.sessionMapper.resetSession(sql);
        System.out.println("把数据写回session");

    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        //exit.exit();
        getUser();
        //exit();
        //待实现
        //程序将要退出前，将sessionMap里的内容写回session表
    }

}
