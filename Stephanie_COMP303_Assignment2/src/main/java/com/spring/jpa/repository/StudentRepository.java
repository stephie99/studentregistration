package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpa.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserName(String userName);
    //Student findByUserId(Long userId);
}

