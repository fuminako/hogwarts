package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Student> getAllStudent() {
        return this.service.getAllStudent();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") long id) {
        return this.service.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.service.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return this.service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        this.service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
