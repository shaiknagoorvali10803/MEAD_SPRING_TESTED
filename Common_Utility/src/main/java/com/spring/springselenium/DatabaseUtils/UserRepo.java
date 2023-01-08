package com.spring.springselenium.DatabaseUtils;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Student,Integer> {
}
