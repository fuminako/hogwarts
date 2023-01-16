package ru.hogwarts.school.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

public class FacultyDto{
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private final Long id;
        private final String name;
        private final String color;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

        public FacultyDto(Long id, String name, String color) {
            this.id = id;
            this.name = name;
            this.color = color;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }

    public Set<Student> getStudents() {
        return students;
    }
}
