package com.gr1.spring.repository;


import com.gr1.spring.repository.base.BaseRepository;


import com.gr1.spring.entity.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface TodoRepository extends BaseRepository<Todo,Long> {
    Optional<Todo> findByUserIdAndId(Long userId,Long id);
    List<Todo> findByUserId(Long userId);
    List<Todo> findByTitleContaining(String title);
    @Query(value = "SELECT * FROM todos t WHERE t.user_id = :user_id" +
            " AND (:title IS NULL OR LOWER(t.title) LIKE %:title%)" +
            " AND (:is_done IS NULL OR t.is_done = :is_done)", nativeQuery = true)
    List<Todo> findByUser_IdAndTitleContainingIgnoreCaseAndIsDone(@Param("user_id") Long userId,@Param("title")String title,@Param("is_done")Boolean isDone);

    @Query(value = "SELECT * FROM todos t WHERE t.user_id = :user_id" +
            " AND (:title IS NULL OR LOWER(t.title) LIKE %:title%)", nativeQuery = true)
    List<Todo> findByUser_IdAndTitleContainingIgnoreCase(@Param("user_id") Long userId,@Param("title")String title);


    @Modifying
    @Query(value="UPDATE Todo t SET t.is_done = :is_done WHERE t.id = :id")
    Integer updateByUser_IdAndStatus(@Param("id") Long todoId    ,@Param("is_done")Boolean status);

    Page<Todo> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<Todo> findByTitleContaining(String title, Sort sort);
}
