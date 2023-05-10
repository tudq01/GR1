package com.gr1.spring.controller;

import com.gr1.spring.controller.base.BaseController;
import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.entity.User;
import com.gr1.spring.mapper.dto.TodoDTOMapper;
import com.gr1.spring.mapper.request.TodoRequestMapper;
import com.gr1.spring.payload.TodoRequest;
import com.gr1.spring.repository.UserRepository;
import com.gr1.spring.security.service.UserService;
import com.gr1.spring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController implements BaseController<TodoRequest> {
    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoDTOMapper dtoMapper;

    @Autowired
    private TodoRequestMapper requestMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    //ok
    @Override
    @GetMapping("")
    public ResponseEntity<?> all() {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
       return ResponseEntity.ok().body(dtoMapper.toTodoDTOs(todoService.findByUserId(userId)));
        //return ResponseEntity.ok().body(todoService.findByUserId(userId));
    }
    // chua xu ly neu id jwt bi sai
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> one(Long id) {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        // check if user login
        Optional<Todo> todo = todoService.findByUserIdAndId(userId,id);
        if(!todo.isPresent()){
            String errorMessage = "Todo not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity
                .ok()
                .body(dtoMapper.toTodoDTO(todoService.findById(id)));
    }


    // fix request mapper
    @Override
    @PostMapping("")
    public ResponseEntity<?> create(TodoRequest entity) {
        System.out.println(entity);
        return ResponseEntity
                .ok()
                .body(dtoMapper.toTodoDTO(todoService.save(requestMapper.toTodo(entity))));
    }


    // add more dto mapper to convert id
    @Override
    @PutMapping("/{id}")

    public ResponseEntity<?> update(TodoRequest entity, Long id) {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        Optional<Todo> todo = todoService.findByUserIdAndId(userId,id);
        if(!todo.isPresent()){
            String errorMessage = "Todo not found with ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        Optional<User> userData = userRepository.findById(userId);
        // filter to  check user exist
        if(userData.isPresent()) {
            Todo todoMap = requestMapper.toTodo(entity);
            todoMap.setUser(userData.get());
            return ResponseEntity
                    .ok()
                    .body(dtoMapper.toTodoDTO(todoService.updateById(id,todoMap)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exist");
    }


    //ok
    @Override
    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(Long id) {
       todoService.deleteById(id);
       return ResponseEntity.noContent().build();
    }

}
