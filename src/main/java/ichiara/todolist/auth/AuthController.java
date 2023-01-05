package ichiara.todolist.auth;

import ichiara.todolist.dto.UserRequest;
import ichiara.todolist.services.UserService;
import ichiara.todolist.utils.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.newUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.login(userRequest));
    }
}
