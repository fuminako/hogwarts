package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Faculty> getAllStudent() {
        return this.service.getAllFaculty();
    }

    @GetMapping("/{id}")
    public Faculty getFacultyById(@PathVariable("id") long id) {
        return this.service.getFacultyById(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return this.service.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        return this.service.updateFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") Long id) {
        this.service.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}


