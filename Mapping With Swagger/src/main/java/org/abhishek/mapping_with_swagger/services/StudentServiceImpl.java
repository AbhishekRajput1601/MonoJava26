package org.abhishek.mapping_with_swagger.services;

import lombok.RequiredArgsConstructor;
import org.abhishek.mapping_with_swagger.dtos.PageResponseDto;
import org.abhishek.mapping_with_swagger.dtos.StudentProfileRequestDto;
import org.abhishek.mapping_with_swagger.dtos.StudentRequestDto;
import org.abhishek.mapping_with_swagger.dtos.StudentResponseDto;
import org.abhishek.mapping_with_swagger.entities.Student;
import org.abhishek.mapping_with_swagger.entities.StudentProfile;
import org.abhishek.mapping_with_swagger.exceptions.DuplicateResourceException;
import org.abhishek.mapping_with_swagger.exceptions.ResourceNotFoundException;
import org.abhishek.mapping_with_swagger.repositories.StudentProfileRepository;
import org.abhishek.mapping_with_swagger.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {
        // Check if profile email already exists
        if (studentProfileRepository.existsByEmail(requestDto.getProfile().getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists: " + requestDto.getProfile().getEmail()
            );
        }


        Student student = modelMapper.map(requestDto, Student.class);
        StudentProfile profile = modelMapper.map(requestDto.getProfile(), StudentProfile.class);


        student.setProfile(profile);
        profile.setStudent(student);

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentResponseDto.class);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize) {
        validatePagination(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> page = studentRepository.findAll(pageable);

        List<StudentResponseDto> content = page.getContent().stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());

        PageResponseDto<StudentResponseDto> response = new PageResponseDto<>();
        response.setContent(content);
        response.setPageNumber(pageNumber);
        response.setPageSize(pageSize);
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = findStudentById(id);
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        Student student = findStudentById(id);


        StudentProfile existingProfile = student.getProfile();
        if (existingProfile != null && !existingProfile.getEmail().equals(requestDto.getProfile().getEmail())) {
            if (studentProfileRepository.existsByEmailAndIdNot(requestDto.getProfile().getEmail(), existingProfile.getId())) {
                throw new DuplicateResourceException(
                        "Email already exists: " + requestDto.getProfile().getEmail()
                );
            }
        }


        student.setFullName(requestDto.getFullName());
        student.setAge(requestDto.getAge());

        StudentProfile profile = student.getProfile();
        if (profile != null) {
            updateProfile(profile, requestDto.getProfile());
        }

        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        studentRepository.delete(student);
    }


    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    private void updateProfile(StudentProfile profile, StudentProfileRequestDto profileRequestDto) {
        profile.setEmail(profileRequestDto.getEmail());
        profile.setPhone(profileRequestDto.getPhone());
        profile.setCity(profileRequestDto.getCity());
    }

    private void validatePagination(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must not be negative");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0");
        }
        if (pageSize > 100) {
            throw new IllegalArgumentException("Page size must not be greater than 100");
        }
    }
}