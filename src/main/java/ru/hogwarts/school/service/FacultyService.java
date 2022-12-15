package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.NoFacultyInListException;
import ru.hogwarts.school.exceptions.NoStudentInListException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private long counter = 0L;

    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    public Faculty addFaculty(Faculty faculty) {
        long newId = counter++;
        faculty.setId(newId);
        facultyMap.put(newId, faculty);
        return faculty;
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        if (this.facultyMap.containsKey(id)) {
            Faculty oldFaculty = this.facultyMap.get(id);
            oldFaculty.setColor(faculty.getColor());
            oldFaculty.setName(faculty.getName());
            return oldFaculty;
        } else {
            throw new NoFacultyInListException("Факультет не найден");
        }
    }

    public Faculty getFacultyById(Long id) {
        if (this.facultyMap.containsKey(id)) {
            return facultyMap.get(id);
        } else {
            throw new NoFacultyInListException("Факультет не найден");
        }
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyMap.values();
    }

    public void deleteFaculty(Long id) {
        if (this.facultyMap.containsKey(id)) {
            this.facultyMap.remove(id);
        } else {
            throw new NoStudentInListException("Факультет не найден");
        }
    }
}

