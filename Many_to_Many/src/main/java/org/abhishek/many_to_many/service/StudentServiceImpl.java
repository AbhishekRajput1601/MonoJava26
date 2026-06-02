package org.abhishek.many_to_many.service;

import org.abhishek.many_to_many.dto.CourseResponseDto;
import org.abhishek.many_to_many.dto.PageResponseDto;
import org.abhishek.many_to_many.dto.StudentRequestDto;
import org.abhishek.many_to_many.dto.StudentResponseDto;
import org.abhishek.many_to_many.exception.BadRequestException;
import org.abhishek.many_to_many.exception.DuplicateResourceException;
import org.abhishek.many_to_many.exception.ResourceNotFoundException;
import org.abhishek.many_to_many.model.Course;
import org.abhishek.many_to_many.model.Student;
import org.abhishek.many_to_many.repository.CourseRepository;
import org.abhishek.many_to_many.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("id", "fullName", "email", "age");

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto dto) {
        log.info("Create student started: {}", dto.getFullName());
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        validateDuplicateCourseIds(dto.getCourseIds());
        Student student = Student.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
        if (dto.getCourseIds() != null && !dto.getCourseIds().isEmpty()) {
            List<Course> courses = getCoursesFromIds(dto.getCourseIds());
            student.setCourses(new HashSet<>(courses));
        }
        Student saved = studentRepository.save(student);
        log.info("Create student completed: {}", saved.getId());
        return mapStudentToResponseDto(saved);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        log.info("Get all students");
        return studentRepository.findAll().stream().map(this::mapStudentToResponseDto).collect(Collectors.toList());
    }

    @Override
    public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        validatePagination(pageNumber, pageSize);
        validateStudentSortField(sortBy);
        Sort.Direction dir = getSortDirection(sortDirection);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(dir, mapSortField(sortBy)));
        Page<Student> page = studentRepository.findAll(pageable);
        List<StudentResponseDto> content = page.getContent().stream().map(this::mapStudentToResponseDto).collect(Collectors.toList());
        return new PageResponseDto<>(content, page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student s = findStudentById(id);
        return mapStudentToResponseDto(s);
    }

    @Override
    public List<CourseResponseDto> getCoursesByStudentId(Long studentId) {
        Student s = findStudentById(studentId);
        return s.getCourses().stream().map(c -> modelMapper.map(c, CourseResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
        log.info("Update student started: {}", id);
        Student student = findStudentById(id);
        if (studentRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new DuplicateResourceException("Email already exists");
        }
        validateDuplicateCourseIds(dto.getCourseIds());
        student.setFullName(dto.getFullName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        // replace courses
        if (dto.getCourseIds() != null) {
            Set<Course> newCourses = new HashSet<>(getCoursesFromIds(dto.getCourseIds()));
            student.setCourses(newCourses);
        }
        Student saved = studentRepository.save(student);
        log.info("Update student completed: {}", saved.getId());
        return mapStudentToResponseDto(saved);
    }

    @Override
    public StudentResponseDto assignCourseToStudent(Long studentId, Long courseId) {
        log.info("Assign course {} to student {}", courseId, studentId);
        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);
        if (student.getCourses().contains(course)) {
            throw new BadRequestException("Course already assigned to student");
        }
        student.addCourse(course);
        Student saved = studentRepository.save(student);
        return mapStudentToResponseDto(saved);
    }

    @Override
    public StudentResponseDto removeCourseFromStudent(Long studentId, Long courseId) {
        log.info("Remove course {} from student {}", courseId, studentId);
        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);
        if (!student.getCourses().contains(course)) {
            throw new BadRequestException("Course not assigned to student");
        }
        student.removeCourse(course);
        Student saved = studentRepository.save(student);
        return mapStudentToResponseDto(saved);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Delete student started: {}", id);
        Student student = findStudentById(id);
        // remove relationships
        for (Course c : student.getCourses()) {
            c.getStudents().remove(student);
            courseRepository.save(c);
        }
        studentRepository.delete(student);
        log.info("Delete student completed: {}", id);
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    private Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    private List<Course> getCoursesFromIds(List<Long> ids) {
        if (ids == null) return Collections.emptyList();
        List<Course> courses = courseRepository.findAllById(ids);
        if (courses.size() != new HashSet<>(ids).size()) {
            throw new ResourceNotFoundException("One or more course IDs not found");
        }
        return courses;
    }

    private void validateDuplicateCourseIds(List<Long> ids) {
        if (ids == null) return;
        Set<Long> set = new HashSet<>(ids);
        if (set.size() != ids.size()) throw new BadRequestException("Duplicate course IDs in request");
    }

    private StudentResponseDto mapStudentToResponseDto(Student s) {
        StudentResponseDto dto = modelMapper.map(s, StudentResponseDto.class);
        List<CourseResponseDto> courses = s.getCourses().stream().map(c -> modelMapper.map(c, CourseResponseDto.class)).collect(Collectors.toList());
        dto.setCourses(courses);
        return dto;
    }

    private void validatePagination(int pageNumber, int pageSize) {
        if (pageNumber < 0) throw new BadRequestException("pageNumber must not be negative");
        if (pageSize <= 0 || pageSize > 100) throw new BadRequestException("pageSize must be between 1 and 100");
    }

    private void validateStudentSortField(String sortBy) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) throw new BadRequestException("Invalid sort field");
    }

    private Sort.Direction getSortDirection(String dir) {
        if ("asc".equalsIgnoreCase(dir)) return Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(dir)) return Sort.Direction.DESC;
        throw new BadRequestException("Invalid sort direction");
    }

    private String mapSortField(String sortBy) {
        return sortBy;
    }
}

