package net.syndicate.journal.controller;

import net.syndicate.journal.entity.JournalEntity;
import net.syndicate.journal.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/journal")
public class Journal {
    @Autowired
    private JournalService journalService;

    @PostMapping("/save-journal")
    public ResponseEntity<String> createJournalData(@RequestBody JournalEntity journalEntity) {
        try {
            return new ResponseEntity<>(journalService.saveJournal(journalEntity), HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failed to create Journal", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-journals")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(journalService.getAllJournals(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get Journals", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-journal-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(journalService.getJournalById(id).orElse(null),  HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failed to get Journal", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-journal/{id}")
    public ResponseEntity<String> updateJournalData(@PathVariable long id, @RequestBody JournalEntity updatedJournalEntity) {
        try {
            return new ResponseEntity<>(journalService.updateJournal(id, updatedJournalEntity),  HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update Journal", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete-journal/{id}")
    public ResponseEntity<String> deleteJournalData(@PathVariable long id) {
        try {
            return new ResponseEntity<>(journalService.deleteJournal(id), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failed to delete Journal", HttpStatus.BAD_REQUEST);
        }

    }

}
