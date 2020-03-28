package com.example.demo.controller;

import com.example.demo.entity.NoteEntity;
import com.example.demo.service.impl.NoteServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    public NoteServiceimpl note;

    @GetMapping(path = "/note_set")
    public Object findNote(int set_id)
    {
        List<NoteEntity>list=note.findNote(set_id);
        return list;
    }
}
