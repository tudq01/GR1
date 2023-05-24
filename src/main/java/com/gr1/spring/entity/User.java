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


    @Column(name = "name",nullable = false,length = 30)
    private String name;


    @Column(name = "password")
    private String password;

}
