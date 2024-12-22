package com.example.userapi.service;


import com.example.userapi.dto.NoteDto;
import com.example.userapi.entity.Note;
import com.example.userapi.entity.User;
import com.example.userapi.repo.NoteRepository;
import com.example.userapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public NoteDto createNote(NoteDto noteDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        return mapToDto(savedNote);
    }

    public List<NoteDto> getAllNotes(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getNotes().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteNoteById(Long noteId, Long userId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        if (!note.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }
        noteRepository.delete(note);
    }

    private NoteDto mapToDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        return noteDto;
    }
}