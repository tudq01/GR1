package com.gr1.spring.repository;


import com.gr1.spring.repository.base.BaseRepository;


import com.gr1.spring.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends BaseRepository<Todo,Long> {
    Optional<Todo> findByUserIdAndId(Long userId,Long id);
    List<Todo> findByUserId(Long userId);

    List<Todo> findByTitleContaining(String title);



}
