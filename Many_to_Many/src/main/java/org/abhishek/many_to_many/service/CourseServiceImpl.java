package org.abhishek.many_to_many.service;

import org.abhishek.many_to_many.dto.CourseRequestDto;
import org.abhishek.many_to_many.dto.CourseResponseDto;
import org.abhishek.many_to_many.dto.PageResponseDto;
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

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    private static final List<String> ALLOWED_SORT_FIELDS = Arrays.asList("id", "courseName", "courseCode", "fees");

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto dto) {
        log.info("Create course started: {}", dto.getCourseName());
        if (courseRepository.existsByCourseCode(dto.getCourseCode())) {
            throw new DuplicateResourceException("Course code already exists");
        }
        if (dto.getFees() == null || dto.getFees() <= 0) {
            throw new BadRequestException("Fees must be greater than zero");
        }
        Course course = Course.builder()
                .courseName(dto.getCourseName())
                .courseCode(dto.getCourseCode())
                .fees(dto.getFees())
                .build();
        Course saved = courseRepository.save(course);
        log.info("Create course completed: {}", saved.getId());
        return modelMapper.map(saved, CourseResponseDto.class);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        log.info("Get all courses");
        return courseRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageResponseDto<CourseResponseDto> getAllCoursesWithPagination(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        validatePagination(pageNumber, pageSize);
        validateCourseSortField(sortBy);
        Sort.Direction dir = getSortDirection(sortDirection);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(dir, mapSortField(sortBy)));
        Page<Course> page = courseRepository.findAll(pageable);
        List<CourseResponseDto> content = page.getContent().stream().map(c -> modelMapper.map(c, CourseResponseDto.class)).collect(Collectors.toList());
        return new PageResponseDto<>(content, page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isLast());
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        Course course = findCourseById(id);
        return modelMapper.map(course, CourseResponseDto.class);
    }

    @Override
    public List<StudentResponseDto> getStudentsByCourseId(Long courseId) {
        Course course = findCourseById(courseId);
        Set<Student> students = course.getStudents();
        return students.stream().map(s -> modelMapper.map(s, StudentResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto dto) {
        log.info("Update course started: {}", id);
        Course course = findCourseById(id);
        if (courseRepository.existsByCourseCodeAndIdNot(dto.getCourseCode(), id)) {
            throw new DuplicateResourceException("Course code already exists");
        }
        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        if (dto.getFees() == null || dto.getFees() <= 0) throw new BadRequestException("Fees must be greater than zero");
        course.setFees(dto.getFees());
        Course saved = courseRepository.save(course);
        log.info("Update course completed: {}", saved.getId());
        return modelMapper.map(saved, CourseResponseDto.class);
    }

    @Override
    public void deleteCourse(Long id) {
        log.info("Delete course started: {}", id);
        Course course = findCourseById(id);
        // remove relationships
        for (Student s : course.getStudents()) {
            s.getCourses().remove(course);
            studentRepository.save(s);
        }
        courseRepository.delete(course);
        log.info("Delete course completed: {}", id);
    }

    private Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    private void validatePagination(int pageNumber, int pageSize) {
        if (pageNumber < 0) throw new BadRequestException("pageNumber must not be negative");
        if (pageSize <= 0 || pageSize > 100) throw new BadRequestException("pageSize must be between 1 and 100");
    }

    private void validateCourseSortField(String sortBy) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) throw new BadRequestException("Invalid sort field");
    }

    private Sort.Direction getSortDirection(String dir) {
        if ("asc".equalsIgnoreCase(dir)) return Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(dir)) return Sort.Direction.DESC;
        throw new BadRequestException("Invalid sort direction");
    }

    private String mapSortField(String sortBy) {
        // map DTO field names to entity fields if needed
        if ("courseName".equals(sortBy)) return "courseName";
        if ("courseCode".equals(sortBy)) return "courseCode";
        return sortBy;
    }
}

