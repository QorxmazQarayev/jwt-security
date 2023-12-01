package com.changesite.springbootsecurityjwt.controller;

import com.changesite.springbootsecurityjwt.dto.UserDto;
import com.changesite.springbootsecurityjwt.dto.UserResponse;
import com.changesite.springbootsecurityjwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/save")
    public ResponseEntity<UserResponse>save(@RequestBody UserDto userDto){
      return ResponseEntity.ok(authenticationService.save(userDto));
    }
}
