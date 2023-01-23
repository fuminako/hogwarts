package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<StudentDto> getAllStudent() {
        return this.service.getAllStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") long id) {
        StudentDto student = service.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto student) {
        return this.service.addStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDto student) {
        StudentDto updatedStudent = service.updateStudent(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        this.service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<Collection<StudentDto>> findByAge(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(service.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("find/between")
    public Collection<StudentDto> findByAgeBetween(@RequestParam("minAge") int minAge,
                                                   @RequestParam("maxAge") int maxAge) {
        return this.service.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/find_faculty")
    public ResponseEntity<Faculty> getFaculty(@RequestParam Long id) {
        if (id >= 0) {
            return ResponseEntity.ok(this.service.getFaculty(id));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public long countStudent() {
        return this.service.countStudent();
    }

    @GetMapping("/average_age")
    public double getStudentAverageAge() {
        return this.service.getStudentAverageAge();
    }

    @GetMapping("/find/last")
    public Collection<StudentDto> findLastStudent() {
        return this.service.findLastStudent();
    }

    @GetMapping("/A")
    public Collection<String> findStudentByLetter() {
        return this.service.findStudentByLetter();
    }

    @GetMapping("/average_age/stream")
    public double getStudentAverageAgeStream() {
        return this.service.getStudentAverageAgeStream();
    }

    @GetMapping("/int")
    public int getInt() {
        return this.service.getInt();
    }
}