package com.gr1.spring.mapper.request;

import com.gr1.spring.entity.User;
import com.gr1.spring.payload.LoginRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {
    @Autowired
    private ModelMapper mapper;
    public User toUser(LoginRequest userRequest){
        return mapper.map(userRequest,User.class);
    }
}
