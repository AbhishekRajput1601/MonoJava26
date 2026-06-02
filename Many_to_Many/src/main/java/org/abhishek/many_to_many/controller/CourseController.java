package org.abhishek.many_to_many.controller;

import org.abhishek.many_to_many.dto.CourseRequestDto;
import org.abhishek.many_to_many.dto.CourseResponseDto;
import org.abhishek.many_to_many.dto.PageResponseDto;
import org.abhishek.many_to_many.dto.StudentResponseDto;
import org.abhishek.many_to_many.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Validated
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto dto) {
        log.info("POST /api/courses");
        CourseResponseDto created = courseService.createCourse(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CourseResponseDto> getAllCourses() {
        log.info("GET /api/courses");
        return courseService.getAllCourses();
    }

    @GetMapping("/page")
    public PageResponseDto<CourseResponseDto> getAllCoursesWithPagination(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        log.info("GET /api/courses/page");
        return courseService.getAllCoursesWithPagination(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{courseId}/students")
    public List<StudentResponseDto> getStudentsByCourseId(@PathVariable Long courseId) {
        log.info("GET /api/courses/{}/students", courseId);
        return courseService.getStudentsByCourseId(courseId);
    }

    @GetMapping("/{id}")
    public CourseResponseDto getCourseById(@PathVariable Long id) {
        log.info("GET /api/courses/{}", id);
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public CourseResponseDto updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDto dto) {
        log.info("PUT /api/courses/{}", id);
        return courseService.updateCourse(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("DELETE /api/courses/{}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

