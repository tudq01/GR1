package com.gr1.spring.controller;

import com.gr1.spring.controller.base.BaseController;
import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.mapper.dto.TodoDTOMapper;
import com.gr1.spring.mapper.request.TodoRequestMapper;
import com.gr1.spring.payload.request.todo.TodoFilterRequest;
import com.gr1.spring.payload.request.todo.TodoRequest;
import com.gr1.spring.payload.request.todo.TodoStatusRequest;
import com.gr1.spring.security.service.UserService;
import com.gr1.spring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
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

    @Override
    @GetMapping("")
    public ResponseEntity<?> all() {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
       return ResponseEntity.ok().body(dtoMapper.toTodoDTOs(todoService.findByUserId(userId)));
        //return ResponseEntity.ok().body(todoService.findByUserId(userId));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> getFilterTodo(@RequestParam(required = false) String title,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size,
                                                             @RequestParam(defaultValue = "id,desc") String[] sort) {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        return ResponseEntity.ok().body(todoService.getAllTodo(userId,title,page,size,sort));
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


    // fix request mapper TodoRequestMapper
    @Override
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody TodoRequest entity) {
        return ResponseEntity
                .ok()
                .body(dtoMapper.toTodoDTO(todoService.save(requestMapper.toTodo(entity))));
    }


    // add more dto mapper to convert id
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TodoRequest entity, Long id) {
            return ResponseEntity
                    .ok()
                    .body(dtoMapper.toTodoDTO(todoService.updateById(id,requestMapper.toTodo(entity))));
    }


    //ok
    @Override
    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(Long id) {
       todoService.deleteById(id);
       return ResponseEntity.noContent().build();
    }


    @GetMapping("/filter")
    public ResponseEntity<?> filterByStatusAndTitle(@RequestParam("title") Optional<String> title,@RequestParam("status") Optional<String> status) {
        // Process the queryParam and return the response

        TodoFilterRequest filterRequest = new TodoFilterRequest(title,status);
        return ResponseEntity.ok().body(todoService.filterByStatusAndTitle(filterRequest));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@RequestBody() TodoStatusRequest request, @PathVariable("id") String todoId){
        System.out.println(todoId+request.getIs_done());
        return ResponseEntity.ok().body(todoService.updateStatus(request,todoId));
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }



    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
