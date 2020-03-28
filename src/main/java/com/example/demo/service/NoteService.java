package com.example.demo.service;

import com.example.demo.entity.NoteEntity;

import java.util.List;

public interface NoteService {
    List<NoteEntity> findNote(int entry_id);
}
