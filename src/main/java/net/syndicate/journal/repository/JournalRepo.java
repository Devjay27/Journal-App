package net.syndicate.journal.repository;

import net.syndicate.journal.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepo extends JpaRepository<JournalEntity, Long> { }
