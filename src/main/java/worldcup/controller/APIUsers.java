package worldcup.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import worldcup.models.User;
import worldcup.service.UserService;
import worldcup.util.dto.CreateUserDTO;
import worldcup.util.dto.UserLoginDTO;

@RestController
@RequestMapping(value = "/api/auth")
public class APIUsers {
    private final UserService service;

    @Autowired
    public APIUsers(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Valid @RequestBody
            CreateUserDTO dto,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User foundUser = service.findOneByEmail(dto.getEmailAddress());

        if (foundUser != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email address already exists!");
        }

        User user = new User(dto);

        User savedUser = service.save(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid
            @RequestBody
            UserLoginDTO dto,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User foundUser = service.findOneByEmail(dto.getEmailAddress());

        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email address does not exist!");
        }

        return null;
    }
}
