package com.gr1.spring.service;

import com.gr1.spring.entity.Todo;
import com.gr1.spring.payload.request.todo.TodoFilterRequest;
import com.gr1.spring.payload.request.todo.TodoStatusRequest;
import com.gr1.spring.service.base.BaseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TodoService extends BaseService<Todo> {
    List<Todo> findByUserId(Long userId);
    Optional<Todo> findByUserIdAndId(Long userId, Long id);
    List<Todo> filterByStatusAndTitle(TodoFilterRequest filterRequest);

    Integer updateStatus(TodoStatusRequest status, String todoId);
    Map<String, Object> getAllTodo(Long userId,String title,int page,int size,String[] sort);
}
