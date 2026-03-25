package net.syndicate.journal.service;

import net.syndicate.journal.entity.JournalEntity;
import net.syndicate.journal.repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {
    @Autowired
    private JournalRepo journalRepo;

    public String saveJournal( JournalEntity journalEntity ) {
        journalEntity.setCreated(LocalDateTime.now());
        journalRepo.save(journalEntity);
        return "Journal " + journalEntity.getId() + " saved successfully!";
    }

    public List<JournalEntity> getAllJournals() {
        return journalRepo.findAll();
    }

    public Optional<JournalEntity> getJournalById(Long id) {
        return journalRepo.findById(id);
    }

    public String updateJournal(Long id, JournalEntity updatedJournalEntity) {
        JournalEntity existingJournalEntity = journalRepo.findById(id).orElse(null);
        if (existingJournalEntity != null) {
            existingJournalEntity.setTitle(updatedJournalEntity.getTitle() != null && updatedJournalEntity.getTitle().isEmpty() ? existingJournalEntity.getTitle() : updatedJournalEntity.getTitle());
            existingJournalEntity.setContent(updatedJournalEntity.getContent() != null && updatedJournalEntity.getContent().isEmpty() ? existingJournalEntity.getContent() : updatedJournalEntity.getContent());
            System.out.println( existingJournalEntity.getTitle() + " " + existingJournalEntity.getContent());
            journalRepo.save(existingJournalEntity);
            return "Journal " + existingJournalEntity.getId() + " updated successfully!";
        }
        else {
            return "Journal " + id + " failed to update";
        }
    }

    public String deleteJournal(Long id) {
        journalRepo.deleteById(id);
        return "Journal " + id + " deleted successfully!";
    }
}
