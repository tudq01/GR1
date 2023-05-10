package com.gr1.spring.mapper.dto;
import com.gr1.spring.dto.TodoDTO;
import com.gr1.spring.entity.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoDTOMapper {
    @Autowired
    private ModelMapper mapper;
    public TodoDTO toTodoDTO(Todo todo){
        return mapper.map(todo,TodoDTO.class);
    }
    public List<TodoDTO> toTodoDTOs(List<Todo> todos) {
        return todos.stream()
                .map(this::toTodoDTO)
                .collect(Collectors.toList());
    }
}
