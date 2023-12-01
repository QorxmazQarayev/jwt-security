package com.changesite.springbootsecurityjwt.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserResponse {
    String token;

}
