package com.craftsmanship.controllers;

import com.craftsmanship.entities.Users;
import com.craftsmanship.exceptions.InternalServerErrorException;
import com.craftsmanship.repositories.UsersRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping
    public ResponseEntity<Users> addUsers(String userName) {
        try {
            Users user = new Users();
            user.setUserName(userName);
            return new ResponseEntity<>(usersRepository.save(user), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to add users: " + exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            return new ResponseEntity<>(usersRepository.findAll(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to add users: " + exception.getMessage());
        }
    }


}
