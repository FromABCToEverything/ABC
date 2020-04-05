package com.example.demo.service;

import com.example.demo.entity.NoteEntity;

import java.util.List;

public interface NoteService {
    List<NoteEntity> findNote(int set_id,int index);
    int insertNote(String title,String content);
}
