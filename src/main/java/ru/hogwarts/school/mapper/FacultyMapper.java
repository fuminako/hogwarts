package ru.hogwarts.school.mapper;

import ru.hogwarts.school.dto.FacultyDto;
import ru.hogwarts.school.model.Faculty;

public class FacultyMapper {
    public static FacultyDto toDto(Faculty faculty) {
        return new FacultyDto(faculty.getId(), faculty.getName(), faculty.getColor());
    }

    public static Faculty toEntity(FacultyDto facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return faculty;
    }
}
