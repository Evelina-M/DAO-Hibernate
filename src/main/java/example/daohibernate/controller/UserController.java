package example.daohibernate.controller;

import example.daohibernate.entity.UserEntity;
import example.daohibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/persons/by-city")
    public List<UserEntity> getPersonsByCity(@RequestParam String city) {
        return userService.getPersonsByCity(city);
    }
}
