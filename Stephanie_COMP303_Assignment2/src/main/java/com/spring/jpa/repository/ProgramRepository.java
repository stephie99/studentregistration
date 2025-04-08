package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.Program;

public interface ProgramRepository extends JpaRepository<Program, String> {
}