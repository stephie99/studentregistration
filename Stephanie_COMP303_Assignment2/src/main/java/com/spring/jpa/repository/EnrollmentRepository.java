package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.jpa.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
