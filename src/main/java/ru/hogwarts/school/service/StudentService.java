package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long studentId, Student student) {
        return this.studentRepository.findById(studentId).map(
                        s -> {
                            s.setName(student.getName());
                            s.setAge(student.getAge());
                            return s;
                        })
                .orElseThrow();
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return this.studentRepository.findById(id).orElseThrow();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return this.studentRepository.findByAge(age);
    }
}
