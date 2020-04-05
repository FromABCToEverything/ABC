package com.example.demo.controller;

import com.example.demo.entity.NoteEntity;
import com.example.demo.service.impl.NoteServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    public NoteServiceimpl note;

    @GetMapping(path = "/note_set")
    public Object findNote(int set_id,int page)
    {
//        int pageSize=20;
//        int index=(page-1)*pageSize;
        List<NoteEntity>list=note.findNote(set_id,page);
        return list;
    }

    @PostMapping(path = "/note")
    public int insertNote( String title, String content)
    {
        int op=note.insertNote( title, content);
        return op;
    }
}
