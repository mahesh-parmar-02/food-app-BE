package com.assigment.inventory_management.repository;

import com.assigment.inventory_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

