package example.daohibernate.controller;

import example.daohibernate.entity.Contact;
import example.daohibernate.entity.UserEntity;
import example.daohibernate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{name}/{surname}/{age}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String name,
                                              @PathVariable String surname,
                                              @PathVariable int age) {
        Contact contact = new Contact(name, surname, age);
        Optional<UserEntity> user = userService.getUser(contact);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
        // Получаем существующего пользователя на основе его контакта
        Optional<UserEntity> existingUserOpt = userService.getUser(user.getContact());

        if (existingUserOpt.isPresent()) {
            UserEntity existingUser = existingUserOpt.get();

            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setCityOfLiving(user.getCityOfLiving());

            UserEntity updatedUser = userService.updateUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{name}/{surname}/{age}")
    public ResponseEntity<Object> deleteUserById(@PathVariable String name,
                                                 @PathVariable String surname,
                                                 @PathVariable int age) {
        Contact contact = new Contact(name, surname, age);
        Optional<UserEntity> existingUserOpt = userService.getUser(contact);
        if (existingUserOpt.isPresent()) {
            userService.deleteUser(contact);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-city")
    public ResponseEntity<Object> getByCityOfLiving(@RequestParam String cityOfLiving) {
        List<UserEntity> users = userService.getUsersByCity(cityOfLiving);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/age-less-than")
    public ResponseEntity<Object> getContactAgeLessThanOrderByContactAge(@RequestParam int age) {
        List<UserEntity> users = userService.getLessThanOrderByAge(age);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/by-name-and-surname")
    public ResponseEntity<UserEntity> getContactNameAndContactSurname(@RequestParam String name,
                                                                      @RequestParam String surname) {
        Optional<UserEntity> userOpt = userService.getNameAndSurname(name, surname);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
