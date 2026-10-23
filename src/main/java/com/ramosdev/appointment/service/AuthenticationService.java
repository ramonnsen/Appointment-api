package com.ramosdev.appointment.service;

import com.ramosdev.appointment.dto.UserRegisterDTO;
import com.ramosdev.appointment.dto.UserRequestLoginDTO;
import com.ramosdev.appointment.infra.security.CustomUserDetails;
import com.ramosdev.appointment.infra.security.TokenService;
import com.ramosdev.appointment.model.User;
import com.ramosdev.appointment.model.enums.Role;
import com.ramosdev.appointment.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User register(UserRegisterDTO registerDTO){
        if(registerDTO.password() == null) throw new RuntimeException("Invalid user");

        String encodedPassword = passwordEncoder.encode(registerDTO.password());
        User user = new User();
        user.setName(registerDTO.name());
        user.setUsername(registerDTO.username());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);

       return userRepository.save(user);
        ///POR ENQUANTO, NAO RETORNAR O USUARIO CRIADO FUTURAMENTE !!!!!!!!!
    }

    public String login(UserRequestLoginDTO loginDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        var authenticate = authenticationManager.authenticate(usernamePassword);


         CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        String token = tokenService.generateToken(customUserDetails);

        return token;
    }
}
