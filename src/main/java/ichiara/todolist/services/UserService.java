package ichiara.todolist.services;

import ichiara.todolist.config.JwtService;
import ichiara.todolist.utils.AuthenticationResponse;
import ichiara.todolist.dto.UserRequest;
import ichiara.todolist.models.User;
import ichiara.todolist.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse newUser(UserRequest userRequest){
        if(userRepository.existsByEmail(userRequest.getEmail()) == 0){

            User user = new User();

            user.setEmail(userRequest.getEmail());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

            userRepository.save(user);
            return AuthenticationResponse.builder()
                    .msj("User created successfully!")
                    .build();
        }else{
            return AuthenticationResponse.builder()
                    .msj("Email is already taken")
                    .build();
        }
    }

    public AuthenticationResponse login(UserRequest userRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userRequest.getEmail(),
                    userRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow();

        String token = jwtService.generateJwtToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
