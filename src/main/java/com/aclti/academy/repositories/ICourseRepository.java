package com.aclti.academy.repositories;

import com.aclti.academy.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Course repository interface
 * @author Alexander Schilling
 */
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
