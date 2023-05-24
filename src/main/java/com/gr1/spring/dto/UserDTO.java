package com.gr1.spring.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.gr1.spring.dto.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserDTO extends BaseDTO {
    private String accessToken;
    //private String refreshToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String refreshToken;

    public UserDTO(String accessToken, Long id, String username) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
    }

    public UserDTO(String accessToken, String refreshToken, Long id, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;

    }
}
