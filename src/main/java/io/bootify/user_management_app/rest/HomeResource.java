package io.bootify.user_management_app.rest;

import io.bootify.user_management_app.domain.Tokens;
import io.bootify.user_management_app.domain.Users;
import io.bootify.user_management_app.repos.TokensRepository;
import io.bootify.user_management_app.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.bootify.user_management_app.exception.ResourceNotFoundException;


import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/api")
public class HomeResource {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokensRepository tokensRepository;

    // Get all users
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Get a user by ID
    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }


    // Create a new user
    @PostMapping("/users")
    public Users createUser(@RequestBody Users user) {
        return usersRepository.save(user);
    }

    // Update an existing user
    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users userDetails) {
        return usersRepository.findById(id).map(user -> {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setJoinedAt(userDetails.getJoinedAt());
            user.setIsActive(userDetails.getIsActive());
            // user.setTokens(userDetails.getTokens());
            return usersRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (!usersRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        usersRepository.deleteById(id);
    }

    @GetMapping("/tokens")
    public List<Tokens> getAllTokens() {
        return tokensRepository.findAll();
    }

    @PostMapping("/tokens")
    public Tokens createToken(@RequestBody Tokens token) {
        return tokensRepository.save(token);
    }
}
