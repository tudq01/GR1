package com.gr1.spring.payload.request.auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    private String name;

    @NotBlank
    private String password;

}

