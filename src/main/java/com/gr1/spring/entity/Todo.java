package com.gr1.spring.entity;
import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gr1.spring.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// ko co getter setter nen k pass dc du lieu
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todos")
public class Todo extends BaseEntity {


    @Column(name = "title",nullable = false,length = 30)
    private String title;

    @Column(name = "\"desc\"", nullable = false, length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "is_done")
    private Boolean is_done;
}
