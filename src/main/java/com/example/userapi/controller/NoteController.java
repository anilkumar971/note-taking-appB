package com.example.userapi.controller;

import com.example.userapi.dto.NoteDto;
import com.example.userapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto, @RequestParam Long userId) {
        NoteDto createdNote = noteService.createNote(noteDto, userId);
        return ResponseEntity.ok(createdNote);
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAllNotes(@RequestParam Long userId) {
        List<NoteDto> notes = noteService.getAllNotes(userId);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId, @RequestParam Long userId) {
        noteService.deleteNoteById(noteId, userId);
        return ResponseEntity.noContent().build();
    }
}