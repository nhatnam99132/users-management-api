package io.bootify.user_management_app.rest;

import io.bootify.user_management_app.domain.Tokens;
import io.bootify.user_management_app.domain.Users;
import io.bootify.user_management_app.repos.TokensRepository;
import io.bootify.user_management_app.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class HomeResource {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokensRepository tokensRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PostMapping("/users")
    public Users createUser(@RequestBody Users user) {
        return usersRepository.save(user);
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
