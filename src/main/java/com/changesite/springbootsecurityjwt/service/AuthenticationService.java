package com.changesite.springbootsecurityjwt.service;

import com.changesite.springbootsecurityjwt.dto.UserDto;
import com.changesite.springbootsecurityjwt.dto.UserResponse;
import com.changesite.springbootsecurityjwt.entity.User;
import com.changesite.springbootsecurityjwt.enums.Role;
import com.changesite.springbootsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public UserResponse save(UserDto userDto){
        User user=User.builder().userName(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nameSurName(userDto.getNameSurname()).role(Role.User).build();
        userRepository.save(user);
        String token=jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
