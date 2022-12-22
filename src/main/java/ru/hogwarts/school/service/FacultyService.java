package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Long id, Faculty faculty) {
        return this.facultyRepository.findById(id).map(
                        f -> {
                            f.setName(faculty.getName());
                            f.setColor(faculty.getColor());
                            return f;
                        })
                .orElseThrow();
    }

    public Faculty getFacultyById(Long id) {
        return this.facultyRepository.findById(id).orElseThrow();
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        return this.facultyRepository.findByColor(color);
    }
}

