package com.project.JournalApp.controller;


import com.project.JournalApp.entity.Journal;
import com.project.JournalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalAppControllerV2 {

    private final Map<String, Journal> journalEntries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<Journal> getJournalEntries() {
        return journalEntryService.getJournalEntries();
    }

    @GetMapping("/{journalId}")
    public Optional<Journal> getJournalById(@PathVariable ObjectId journalId) {
        return journalEntryService.getJournalEntryById(journalId);
    }

    @PostMapping
    public Journal createJournal(@RequestBody Journal journal) {
        journalEntryService.saveJournalEntry(journal);
        return journal;
    }

    @PutMapping("/{journalId}")
    public Journal updateJournal(@PathVariable ObjectId journalId, @RequestBody Journal journal) {
        journalEntryService.updateJournalEntry(journalId, journal);
        return journal;
    }

    @DeleteMapping("/{journalId}")
    public boolean deleteJournal(@PathVariable ObjectId journalId) {
        return journalEntryService.deleteJournalEntryById(journalId);
    }
}
