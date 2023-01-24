package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.mapper.FacultyMapper;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public FacultyDto addFaculty(FacultyDto faculty) {
        logger.info("A method was called: addFaculty");
        return FacultyMapper.toDto(this.facultyRepository.save(FacultyMapper.toEntity(faculty)));
    }

    public FacultyDto updateFaculty(Long id, FacultyDto facultyDTO) {
        logger.info("A method was called: updateFaculty");
        Faculty faculty = this.facultyRepository.findById(id).orElseThrow();
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return FacultyMapper.toDto(this.facultyRepository.save(faculty));
    }

    public FacultyDto getFacultyById(Long id) {
        logger.info("A method was called: getFacultyById");
        return FacultyMapper.toDto(this.facultyRepository.findById(id).orElseThrow());
    }

    public Collection<FacultyDto> getAllFaculty() {
        logger.info("A method was called: getAllFaculty");
        return facultyRepository.findAll().stream().map(FacultyMapper::toDto).collect(Collectors.toList());
    }

    public void deleteFaculty(Long id) {
        logger.info("A method was called: deleteFaculty");
        Faculty faculty = this.facultyRepository.findById(id).orElseThrow();
        facultyRepository.delete(faculty);
    }

    public Collection<FacultyDto> findByColor(String color) {
        logger.info("A method was called: findByColor");
        return this.facultyRepository.findByColor(color).stream().map(FacultyMapper::toDto).collect(Collectors.toList());
    }

    public Collection<FacultyDto> findByColorOrName(String colorOrNane) {
        logger.info("A method was called: findByColorOrName");
        return this.facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrNane, colorOrNane)
                .stream().map(FacultyMapper::toDto).collect(Collectors.toList());
    }

    public Collection<Student> findStudent(Long id) {
        logger.info("A method was called: findStudent");
        Faculty faculty = this.facultyRepository.findById(id).orElseThrow();
        return faculty.getStudents();
    }

    public String getFacultyLongestName() {
        List<String> facultyNameList = facultyRepository.findAll().stream().map(Faculty::getName).toList();
        return facultyNameList.stream().max(String::compareTo).get();
    }
}

