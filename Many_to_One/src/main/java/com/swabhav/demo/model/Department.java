package com.swabhav.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="department_name", unique=true, nullable=false)
    private String departmentName;

    @Column(name="location", nullable = false)
    private String location;

    @OneToMany(
            mappedBy="department",
            cascade=CascadeType.ALL,
            orphanRemoval=true,
            fetch=FetchType.EAGER)
            private List<Employee> employees;
}
