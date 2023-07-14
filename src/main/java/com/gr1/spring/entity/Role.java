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

@Table(name="roles")
public class Role extends BaseEntity {


    @Column(name = "name",nullable = false,length = 50)
    private String name;




}
