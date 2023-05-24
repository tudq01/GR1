package com.gr1.spring.mapper.request;

import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.entity.User;
import com.gr1.spring.exception.CustomValidationException;
import com.gr1.spring.payload.TodoRequest;
import com.gr1.spring.repository.UserRepository;

import com.gr1.spring.security.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoRequestMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    public Todo toTodo(TodoRequest todoRequest) {

        UserDTO userAuth = userService.getUserDetail();
        Long userId = userAuth.getId();

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {

            Todo todo = new Todo();
            todo.setTitle(todoRequest.getTitle());

            todo.setDescription(todoRequest.getDescription());
            todo.setIs_done(todoRequest.getIs_done());
            todo.setUser(user.get());
            return todo;
        }
            throw new CustomValidationException("No user found");

    }
}

