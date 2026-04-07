package net.syndicate.journal.service;

import jakarta.transaction.Transactional;
import net.syndicate.journal.entity.JournalEntity;
import net.syndicate.journal.entity.UserEntity;
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

    @Autowired
    private UserService userService;

    @Transactional
    public String saveJournal( JournalEntity journalEntity, String username ) {
        UserEntity user = userService.getByUsername(username);
        journalEntity.setCreated(LocalDateTime.now());
        journalEntity.setUser(user);
        JournalEntity newJournal = journalRepo.save(journalEntity);
        user.getJournals().add(newJournal);
        userService.saveUser(user);
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
            journalRepo.save(existingJournalEntity);
            return "Journal " + existingJournalEntity.getId() + " updated successfully!";
        }
        else {
            return "Journal " + id + " failed to update";
        }
    }

    public void deleteJournal(Long id) {
        journalRepo.deleteById(id);
    }
}
