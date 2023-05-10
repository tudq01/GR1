package com.gr1.spring.repository;


import com.gr1.spring.entity.User;
import com.gr1.spring.repository.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User,Long> {
   Optional<User> findByName(String name);
}
