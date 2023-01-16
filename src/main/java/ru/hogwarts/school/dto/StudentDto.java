package ru.hogwarts.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hogwarts.school.model.Faculty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class StudentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    private final String name;
    private final int age;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public StudentDto(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
