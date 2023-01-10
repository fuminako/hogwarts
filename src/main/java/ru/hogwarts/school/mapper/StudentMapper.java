package ru.hogwarts.school.mapper;


import ru.hogwarts.school.dto.StudentDto;

import ru.hogwarts.school.model.Student;

public class StudentMapper {
    public static StudentDto toDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getAge());
    }

    public static Student toEntity(StudentDto studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        return student;
    }
}
