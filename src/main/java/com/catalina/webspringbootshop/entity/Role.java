package com.catalina.webspringbootshop.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Role {
    //@Column(name = "role_id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "role_name")
    private String name;
}
