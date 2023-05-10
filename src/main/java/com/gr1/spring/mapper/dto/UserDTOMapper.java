package com.gr1.spring.mapper.dto;
import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOMapper {
    @Autowired
    private ModelMapper mapper;
    public UserDTO toUserDTO(Todo todo){
        return mapper.map(todo,UserDTO.class);
    }
    public List<UserDTO> toCategoryDTOs(List<Todo> todos) {
        return todos.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }
}
