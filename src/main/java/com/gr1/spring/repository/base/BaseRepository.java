package com.gr1.spring.repository.base;


import com.gr1.spring.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity,Long> extends JpaRepository<T,Long> {
}
