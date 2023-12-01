package com.changesite.springbootsecurityjwt.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserDto {
    String nameSurname;
    String username;
    String password;
}

