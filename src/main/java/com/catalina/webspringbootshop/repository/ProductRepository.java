package com.catalina.webspringbootshop.repository;

import com.catalina.webspringbootshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Role,Integer> {

}
