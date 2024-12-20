package com.example.EduBridge.dao;

import com.javacorner.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
