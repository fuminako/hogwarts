package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDto addFaculty(FacultyDto faculty) {
        return FacultyMapper.toDto(this.facultyRepository.save(FacultyMapper.toEntity(faculty)));
    }

    public FacultyDto updateFaculty(Long id, FacultyDto facultyDTO) {
        Faculty faculty = this.facultyRepository.findById(id).orElseThrow();
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return FacultyMapper.toDto(this.facultyRepository.save(faculty));
    }

    public FacultyDto getFacultyById(Long id) {
        return FacultyMapper.toDto(this.facultyRepository.findById(id).orElseThrow());
    }

    public Collection<FacultyDto> getAllFaculty() {
        return facultyRepository.findAll().stream().map(FacultyMapper::toDto).collect(Collectors.toList());
    }

    public void deleteFaculty(Long id) {
        Faculty faculty = this.facultyRepository.findById(id).orElseThrow();
        facultyRepository.delete(faculty);
    }

    public Collection<FacultyDto> findByColor(String color) {
        return this.facultyRepository.findByColor(color).stream().map(FacultyMapper::toDto).collect(Collectors.toList());
    }

    public Collection<FacultyDto> findByColorOrName(String colorOrNane) {
        return this.facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrNane, colorOrNane)
                .stream().map(FacultyMapper::toDto).collect(Collectors.toList());
    }

    public Collection<Student> findStudent(Long id) {
        Faculty faculty = this.facultyRepository.findById(id).orElseThrow();
        return faculty.getStudents();
    }
}

