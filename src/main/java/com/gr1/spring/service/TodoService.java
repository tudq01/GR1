package com.gr1.spring.service;

import com.gr1.spring.entity.Todo;
import com.gr1.spring.payload.TodoFilterRequest;
import com.gr1.spring.payload.TodoRequest;
import com.gr1.spring.payload.TodoStatusRequest;
import com.gr1.spring.service.base.BaseService;

import java.util.List;
import java.util.Optional;

public interface TodoService extends BaseService<Todo> {
    List<Todo> findByUserId(Long userId);
    Optional<Todo> findByUserIdAndId(Long userId, Long id);
    List<Todo> filterByStatusAndTitle(TodoFilterRequest filterRequest);

    Integer updateStatus(TodoStatusRequest status, String todoId);
}
