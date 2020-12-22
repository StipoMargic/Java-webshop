package com.catalina.webspringbootshop.repository;

import com.catalina.webspringbootshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
