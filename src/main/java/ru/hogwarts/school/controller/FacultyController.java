package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDto;

import ru.hogwarts.school.model.Student;
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
    public Collection<FacultyDto> getAllStudent() {
        return this.service.getAllFaculty();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable("id") long id) {
        FacultyDto faculty = service.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public FacultyDto createFaculty(@RequestBody FacultyDto faculty) {
        return this.service.addFaculty(faculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable("id") Long id, @RequestBody FacultyDto faculty) {
        FacultyDto updatedFaculty = service.updateFaculty(id, faculty);
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

    @GetMapping("/find/color")
    public ResponseEntity<Collection<FacultyDto>> findByColor(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(service.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/find")
    public ResponseEntity<Collection<FacultyDto>> findByColorOrName(@RequestParam(required = false) String colorOrName) {
        if (colorOrName != null && !colorOrName.isBlank()) {
            return ResponseEntity.ok(service.findByColorOrName(colorOrName));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/find_students")
    public ResponseEntity<Collection<Student>> getStudent(@RequestParam Long id) {
        if (id >= 0) {
            return ResponseEntity.ok(service.findStudent(id));
        }
        return ResponseEntity.ok((Collections.emptyList()));
    }
}


