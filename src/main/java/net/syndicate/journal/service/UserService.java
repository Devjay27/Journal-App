package net.syndicate.journal.service;

import jakarta.transaction.Transactional;
import net.syndicate.journal.entity.UserEntity;
import net.syndicate.journal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public String saveUser(UserEntity userEntity) {
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userRepo.save(userEntity);
        return "User " + userEntity.getId() + " has been created successfully";
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> getUserById(long id) {
        return userRepo.findById(id);
    }

    public String updateUser(UserEntity updatedUserEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity existingUserEntity = userRepo.getByUsername(userName);

        if (existingUserEntity != null) {
            existingUserEntity.setFirstname(updatedUserEntity.getFirstname() != null && updatedUserEntity.getFirstname().isEmpty() ? existingUserEntity.getFirstname() : updatedUserEntity.getFirstname());
            existingUserEntity.setLastname(updatedUserEntity.getLastname() != null && updatedUserEntity.getLastname().isEmpty() ? existingUserEntity.getLastname() : updatedUserEntity.getLastname());
            existingUserEntity.setEmail(updatedUserEntity.getEmail() != null && updatedUserEntity.getEmail().isEmpty() ? existingUserEntity.getEmail() : updatedUserEntity.getEmail());
            userRepo.save(existingUserEntity);
            return "User " + existingUserEntity.getUsername() + " has been updated successfully";
        }
        else  {
            return "User " + userName + " failed to update";
        }
    }

    public String updatePassword(String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity existingUserEntity = userRepo.getByUsername(userName);
        if (existingUserEntity != null) {
            existingUserEntity.setPassword(encoder.encode(newPassword));
            userRepo.save(existingUserEntity);
            return "Password for" + existingUserEntity.getUsername() + " has been updated successfully";
        }
        else {
            return "Password for " + userName + " failed to update";
        }
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public void deleteByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepo.deleteByUsername(userName);
    }

    public UserEntity getByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}
