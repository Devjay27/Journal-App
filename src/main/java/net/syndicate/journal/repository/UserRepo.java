package net.syndicate.journal.repository;

import net.syndicate.journal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity getByUsername(String username);
}
