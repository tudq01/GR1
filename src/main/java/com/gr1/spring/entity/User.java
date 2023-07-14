package com.gr1.spring.entity;
import javax.persistence.*;
import javax.validation.constraints.Size;

import com.gr1.spring.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String password) {
        this.name=name;
        this.password=password;
    }
}
