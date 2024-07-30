package kr.co.myservice.restfulapi.controller;

import kr.co.myservice.restfulapi.bean.User;
import kr.co.myservice.restfulapi.dao.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> findAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User findByIdUser(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
