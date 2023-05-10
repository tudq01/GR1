package com.gr1.spring.entity;
import javax.persistence.*;
import javax.validation.constraints.Size;

import com.gr1.spring.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name="users")
public class User extends BaseEntity {

    @Getter
    @Setter
    @Column(name = "name",nullable = false,length = 30)
    @Size(max=30, message = "Username from 1-30 characters")
    private String name;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

}
