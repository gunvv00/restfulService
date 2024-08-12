package kr.co.myservice.restfulapi.controller;

import jakarta.validation.Valid;
import kr.co.myservice.restfulapi.bean.User;
import kr.co.myservice.restfulapi.dao.UserDaoService;
import kr.co.myservice.restfulapi.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> findByIdUser(@PathVariable int id) {
        User user = service.findById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] Not Found", id));
        }

        EntityModel entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).findAllUsers());
        entityModel.add(linTo.withRel("all-users")); // all-users -> localhost:8088/users

        return entityModel;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = service.deleteUser(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] Not Found", id));
        }

        return ResponseEntity.noContent().build();
    }

}
