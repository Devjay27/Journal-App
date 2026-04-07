package net.syndicate.journal.service;

import net.syndicate.journal.entity.UserEntity;
import net.syndicate.journal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String saveUser(UserEntity userEntity) {
        userRepo.save(userEntity);
        return "User " + userEntity.getId() + " has been created successfully";
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> getUserById(long id) {
        return userRepo.findById(id);
    }

    public String updateUser(long id, UserEntity updatedUserEntity) {
        UserEntity existingUserEntity = userRepo.findById(id).orElse(null);
        if (existingUserEntity != null) {
            existingUserEntity.setFirstname(updatedUserEntity.getFirstname() != null && updatedUserEntity.getFirstname().isEmpty() ? existingUserEntity.getFirstname() : updatedUserEntity.getFirstname());
            existingUserEntity.setLastname(updatedUserEntity.getLastname() != null && updatedUserEntity.getLastname().isEmpty() ? existingUserEntity.getLastname() : updatedUserEntity.getLastname());
            existingUserEntity.setEmail(updatedUserEntity.getEmail() != null && updatedUserEntity.getEmail().isEmpty() ? existingUserEntity.getEmail() : updatedUserEntity.getEmail());
            userRepo.save(existingUserEntity);
            return "User " + existingUserEntity.getId() + " has been updated successfully";
        }
        else  {
            return "User " + id + " failed to update";
        }
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    public UserEntity getByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}
