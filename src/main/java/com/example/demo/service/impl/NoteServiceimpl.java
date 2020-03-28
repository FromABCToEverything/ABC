package com.example.demo.service.impl;

import com.example.demo.entity.NoteEntity;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteServiceimpl implements NoteService {
    @Autowired
    private NoteMapper note;
    @Override
    public List<NoteEntity> findNote(int entry_id) {
        List<NoteEntity> list= note.findNote(entry_id);
        return list;
    }

}
