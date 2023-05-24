package com.gr1.spring.service.impl;


import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.exception.CustomValidationException;
import com.gr1.spring.mapper.dto.TodoDTOMapper;
import com.gr1.spring.payload.TodoFilterRequest;
import com.gr1.spring.payload.TodoRequest;
import com.gr1.spring.payload.TodoStatusRequest;
import com.gr1.spring.repository.TodoRepository;

import com.gr1.spring.security.service.UserService;
import com.gr1.spring.service.TodoService;
import com.gr1.spring.service.base.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl  extends BaseServiceImpl<Todo> implements TodoService{

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoDTOMapper dtoMapper;
    @Autowired
    private UserService userService;


    public TodoServiceImpl(TodoRepository todoRepository) {
        super(todoRepository);
    }

    @Override
    @Transactional
    public Todo updateById(Long id, Todo newEntity) {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        Optional<Todo> todo = findByUserIdAndId(userId,id);
        if(!todo.isPresent()){
            String errorMessage = "Todo not found with ID: " + id;
            throw new CustomValidationException(errorMessage);
        }
        Todo oldEntity = this.findById(id);
        newEntity.setId(oldEntity.getId());
        return super.updateById(id, newEntity);
    }

    @Override
    public List<Todo> findByUserId(Long userId){

        return todoRepository.findByUserId(userId);
    }
    @Override
    public Optional<Todo> findByUserIdAndId(Long userId, Long id){
        return todoRepository.findByUserIdAndId(userId,id);
    }

    @Override
    public List<Todo> filterByStatusAndTitle(TodoFilterRequest filterRequest){
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        String title = filterRequest.getTitle().isPresent() ? filterRequest.getTitle().get() :null;
        if(filterRequest.getStatus().isPresent()&&filterRequest.getStatus().get().contains("all")){
            return  todoRepository.findByUser_IdAndTitleContainingIgnoreCase(userId,title);
        }
        Boolean status;
        status = filterRequest.getStatus().isPresent() ? Boolean.parseBoolean(filterRequest.getStatus().get()):null;
        return  todoRepository.findByUser_IdAndTitleContainingIgnoreCaseAndIsDone(userId,title,status);
    }

    @Transactional
    public Integer updateStatus(TodoStatusRequest status, String todoId){
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        Long id = Long.parseLong(todoId);
        Optional<Todo> todo = todoRepository.findByUserIdAndId(userId,id);
        if(!todo.isPresent()){
            throw new CustomValidationException("No todo found");
        }
        return todoRepository.updateByUser_IdAndStatus(id,status.getIs_done());
    }
}
