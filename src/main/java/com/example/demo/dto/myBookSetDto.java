package com.example.demo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class myBookSetDto {
    @Id
    @GeneratedValue
    private int set_id;
    private String set_name;
    private String collected;
    public myBookSetDto(){}

    @Basic
    @Column(name = "set_id", nullable = false)
    public int getSet_id()
    {
        return set_id;
    }
    @Basic
    @Column(name = "set_name", nullable = false)
    public String getSet_name()
    {
        return set_name;
    }
    @Basic
    @Column(name = "collected", nullable = false)
    public String getCollected()
    {
        return collected;
    }
    public void setSet_id(int set_id){
        this.set_id=set_id;
    }
    public void setSet_name(String set_name){
        this.set_name=set_name;
    }
    public void setCollected(String collected)
    {
        this.collected=collected;
    }

}
