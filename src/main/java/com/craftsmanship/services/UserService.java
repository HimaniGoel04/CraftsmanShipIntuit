package com.craftsmanship.services;

import com.craftsmanship.entities.Users;
import com.craftsmanship.exceptions.InternalServerErrorException;
import com.craftsmanship.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> gerUsers(List<String> userIds){
        try{
            return usersRepository.findAllById(userIds);
        }catch (Exception exception) {
            throw new InternalServerErrorException("Failed to fetch Users: "+exception.getMessage());
        }
    }
}
