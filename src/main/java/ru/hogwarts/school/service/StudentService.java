package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentDto addStudent(StudentDto studentDto) {
        return StudentMapper.toDto(this.studentRepository.save(StudentMapper.toEntity(studentDto)));
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student student = this.studentRepository.findById(id).orElseThrow();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        return StudentMapper.toDto(this.studentRepository.save(student));
    }

    public StudentDto getStudentById(Long id) {
        return StudentMapper.toDto(this.studentRepository.findById(id).orElseThrow());
    }

    public Collection<StudentDto> getAllStudent() {
        return studentRepository.findAll().stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
    }

    public Collection<StudentDto> findByAge(int age) {
        return this.studentRepository.findByAge(age).stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    public Collection<StudentDto> findByAgeBetween(int minAge, int maxAge) {
        return this.studentRepository
                .findByAgeBetween(minAge, maxAge)
                .stream().
                map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Faculty getFaculty(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow();
        return student.getFaculty();
    }

    public long countStudent() {
        return this.studentRepository.countStudent();
    }

    public double getStudentAverageAge() {
        return this.studentRepository.getStudentAverageAge();
    }

    public Collection<StudentDto> findLastStudent() {
        return this.studentRepository.findLastStudent();
    }

}
