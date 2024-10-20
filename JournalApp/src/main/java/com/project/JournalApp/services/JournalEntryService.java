package com.project.JournalApp.services;

import com.project.JournalApp.entity.Journal;
import com.project.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;//generates an implementation for us at runtime

    public void saveJournalEntry(Journal journal){
        journal.setDate(LocalDateTime.now());
        journalEntryRepository.save(journal);
    }

    public Optional<Journal> getJournalEntryById(ObjectId journalId){
        return journalEntryRepository.findById(journalId);
    }

    public boolean deleteJournalEntryById(ObjectId journalId){
        journalEntryRepository.deleteById(journalId);
        return true;
    }

    public List<Journal> getJournalEntries(){
        return journalEntryRepository.findAll();
    }

    public void updateJournalEntry(ObjectId journalId, Journal journal) {
        journal.setDate(LocalDateTime.now());
        Journal existingJournal = journalEntryRepository.findById(journalId).orElse(null);
        if (existingJournal != null) {
            existingJournal.setTitle(journal.getTitle() != null ? journal.getTitle() : existingJournal.getTitle());
            existingJournal.setDescription(journal.getDescription() != null ? journal.getDescription() : existingJournal.getDescription());
            existingJournal.setDate(journal.getDate() !=null ? journal.getDate() : existingJournal.getDate());
            journalEntryRepository.save(existingJournal);
        }

    }
}
