package example.daohibernate.controller;

import example.daohibernate.entity.Contact;
import example.daohibernate.entity.UserEntity;
import example.daohibernate.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class UserController {
    private final UserService userService;

    @GetMapping("/public")
    public String publicPage() {
        return "public text";
    }

    @Secured("ROLE_READ")
    @GetMapping("/read")
    public String read() {
        return "Read content";
    }

    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/write")
    public String write() {
        return "Write content";
    }

    @Secured("ROLE_DELETE")
    @GetMapping("/delete")
    public String delete() {
        return "Delete content, accessible only to DELETE user.";
    }

    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    @GetMapping("/modify")
    public String modify() {
        return "Modify content";
    }

    @GetMapping("/match")
    public String match(@RequestParam String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName().equals(username)) {
            return "Matched Username content";
        }
        return "No Match";
    }
}
