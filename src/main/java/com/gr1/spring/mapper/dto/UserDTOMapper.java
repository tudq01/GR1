package com.gr1.spring.mapper.dto;
import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.entity.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOMapper {
    @Autowired
    private ModelMapper mapper;
    public UserDTO toUserDTO(User user){
        return mapper.map(user,UserDTO.class);
    }
    public List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }
}
