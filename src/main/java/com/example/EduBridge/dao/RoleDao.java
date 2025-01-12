package com.example.EduBridge.dao;

import com.example.EduBridge.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
