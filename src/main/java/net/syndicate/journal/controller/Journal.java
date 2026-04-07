package net.syndicate.journal.controller;

import net.syndicate.journal.entity.JournalEntity;
import net.syndicate.journal.entity.UserEntity;
import net.syndicate.journal.service.JournalService;
import net.syndicate.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/journal")
public class Journal {
    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @PostMapping("/save-journal/{username}")
    public ResponseEntity<String> createJournalData(@RequestBody JournalEntity journalEntity, @PathVariable String username) {
        try {
            return new ResponseEntity<>(journalService.saveJournal(journalEntity, username), HttpStatus.CREATED);
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
            return new ResponseEntity<>("Failed to get Journals", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-journal-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(journalService.getJournalById(id).orElse(null),  HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failed to get Journal", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-journal-by-username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        try {
            UserEntity user =  userService.getByUsername(username);
            List<JournalEntity> allJournals = user.getJournals();
            return new ResponseEntity<>(allJournals, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to get Journal", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Void> deleteJournalData(@PathVariable long id) {
        try {
            journalService.deleteJournal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
