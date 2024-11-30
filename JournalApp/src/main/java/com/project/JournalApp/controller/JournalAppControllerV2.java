package com.project.JournalApp.controller;


import com.project.JournalApp.entity.Journal;
import com.project.JournalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalAppControllerV2 {

    private final Map<String, Journal> journalEntries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getJournalEntries() {
        List<Journal> journalEntries = journalEntryService.getJournalEntries();
        if(!journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{journalId}")
    public ResponseEntity<Journal> getJournalById(@PathVariable ObjectId journalId) {
        Optional<Journal> journalEntryById = journalEntryService.getJournalEntryById(journalId);
        return journalEntryById.map(journal -> new ResponseEntity<>(journal, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createJournal(@RequestBody Journal journal) {
        journalEntryService.saveJournalEntry(journal);
        return new ResponseEntity<>(journal, HttpStatus.CREATED);
    }

    @PutMapping("/{journalId}")
    public ResponseEntity<?> updateJournal(@PathVariable ObjectId journalId, @RequestBody Journal journal) {
        journalEntryService.updateJournalEntry(journalId, journal);
        Optional<Journal> journalEntryById = journalEntryService.getJournalEntryById(journalId);
        if(journalEntryById.isPresent())
        {
            return new ResponseEntity<>(journalEntryById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{journalId}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId journalId) {
        journalEntryService.deleteJournalEntryById(journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
