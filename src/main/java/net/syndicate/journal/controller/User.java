package net.syndicate.journal.controller;

import net.syndicate.journal.entity.UserEntity;
import net.syndicate.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class User {
    @Autowired
    private UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<String> saveUser(@RequestBody UserEntity userEntity) {
        try {
            return new ResponseEntity<>(userService.saveUser(userEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create User", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get Users", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id).orElse(null), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to get User", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody UserEntity userEntity) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, userEntity), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to update User", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
