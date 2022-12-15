package com.example.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity(name = "sample_member")
public class CrudEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int age;
    public CrudEntity(){

    }
    public CrudEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }


}
