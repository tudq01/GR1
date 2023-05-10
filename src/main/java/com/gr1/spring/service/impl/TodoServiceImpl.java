package com.gr1.spring.service.impl;


import com.gr1.spring.entity.Todo;
import com.gr1.spring.mapper.dto.TodoDTOMapper;
import com.gr1.spring.payload.TodoRequest;
import com.gr1.spring.repository.TodoRepository;

import com.gr1.spring.service.TodoService;
import com.gr1.spring.service.base.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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


    public TodoServiceImpl(TodoRepository todoRepository) {
        super(todoRepository);
    }

    @Override
    @Transactional
    public Todo updateById(Long id, Todo newEntity) {
       Todo oldEntity = this.findById(id);
        // this field cannot update
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
}
