package com.project.JournalApp.controller;


import com.project.JournalApp.entity.Journal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalAppController {

    private final Map<String, Journal> journalEntries = new HashMap<>();

    @GetMapping
    public List<Journal> getJournalEntries() {
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("/{journalId}")
    public Journal getJournalById(@PathVariable String journalId) {
        return journalEntries.get(journalId);
    }

    @PostMapping
    public boolean createJournal(@RequestBody Journal journal) {
        journalEntries.put(journal.getJournalId(), journal);
        return true;
    }

    @PutMapping("/{journalId}")
    public boolean updateJournal(@PathVariable String journalId, @RequestBody Journal journal) {
        journalEntries.put(journalId, journal);
        return true;
    }

    @DeleteMapping("/{journalId}")
    public boolean deleteJournal(@PathVariable String journalId) {
        if(journalEntries.containsKey(journalId))
        {
            journalEntries.remove(journalId);
            return true;
        }
        return false;
    }
}
