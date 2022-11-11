package com.aclti.academy.controllers;

import com.aclti.academy.models.Course;
import com.aclti.academy.repositories.ICourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    private final ICourseRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    public CourseController(ICourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable long id) {
        Optional<Course> course = repository.findById(id);

        if (course.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
