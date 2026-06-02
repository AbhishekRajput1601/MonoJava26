package org.abhishek.many_to_many.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private Double fees;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}

