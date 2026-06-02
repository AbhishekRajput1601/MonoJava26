package org.abhishek.many_to_many.controller;

import org.abhishek.many_to_many.dto.CourseResponseDto;
import org.abhishek.many_to_many.dto.PageResponseDto;
import org.abhishek.many_to_many.dto.StudentRequestDto;
import org.abhishek.many_to_many.dto.StudentResponseDto;
import org.abhishek.many_to_many.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@jakarta.validation.Valid @RequestBody StudentRequestDto dto) {
        log.info("POST /api/students");
        StudentResponseDto created = studentService.createStudent(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        log.info("GET /api/students");
        return studentService.getAllStudents();
    }

    @GetMapping("/page")
    public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        log.info("GET /api/students/page");
        return studentService.getAllStudentsWithPagination(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{studentId}/courses")
    public List<CourseResponseDto> getCoursesByStudentId(@PathVariable Long studentId) {
        log.info("GET /api/students/{}/courses", studentId);
        return studentService.getCoursesByStudentId(studentId);
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        log.info("GET /api/students/{}", id);
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @jakarta.validation.Valid @RequestBody StudentRequestDto dto) {
        log.info("PUT /api/students/{}", id);
        return studentService.updateStudent(id, dto);
    }

    @PutMapping("/{studentId}/courses/{courseId}")
    public StudentResponseDto assignCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        log.info("PUT /api/students/{}/courses/{}", studentId, courseId);
        return studentService.assignCourseToStudent(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public StudentResponseDto removeCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        log.info("DELETE /api/students/{}/courses/{}", studentId, courseId);
        return studentService.removeCourseFromStudent(studentId, courseId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("DELETE /api/students/{}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}


