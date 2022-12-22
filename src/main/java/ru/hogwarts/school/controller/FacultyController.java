package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
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
    public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") long id) {
        Faculty faculty = service.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return this.service.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        Faculty updatedFaculty = service.updateFaculty(id, faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") Long id) {
        this.service.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam(required = false) String color){
        if (color!=null && !color.isBlank()){
            return ResponseEntity.ok(service.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}


