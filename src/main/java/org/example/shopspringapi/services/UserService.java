package org.example.shopspringapi.services;

import lombok.Getter;
import lombok.Setter;
import org.example.shopspringapi.models.User;
import org.example.shopspringapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(User user){
            UUID uuid = UUID.randomUUID();
            long id = uuid.getMostSignificantBits() & Long.MAX_VALUE;
            user.setId(id);
            return userRepository.save(user);
        }
    public User updateUser(Long userID,User userDetails){
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userID));
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(long userID){
        userRepository.deleteById(userID);
    }

    public User getUserByID(long userID){
        return  userRepository.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userID));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
