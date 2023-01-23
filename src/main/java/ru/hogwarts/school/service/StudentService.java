package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.mapper.StudentMapper;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentDto addStudent(StudentDto studentDto) {
        logger.info("A method was called: addFaculty");
        return StudentMapper.toDto(this.studentRepository.save(StudentMapper.toEntity(studentDto)));
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        logger.info("A method was called: updateStudent");
        Student student = this.studentRepository.findById(id).orElseThrow();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        return StudentMapper.toDto(this.studentRepository.save(student));
    }

    public StudentDto getStudentById(Long id) {
        logger.info("A method was called: getStudentById");
        return StudentMapper.toDto(this.studentRepository.findById(id).orElseThrow());
    }

    public Collection<StudentDto> getAllStudent() {
        logger.info("A method was called: getAllStudent");
        return studentRepository.findAll().stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        logger.info("A method was called: deleteStudent");
        Student student = this.studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
    }

    public Collection<StudentDto> findByAge(int age) {
        logger.info("A method was called: findByAge");
        return this.studentRepository.findByAge(age).stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    public Collection<StudentDto> findByAgeBetween(int minAge, int maxAge) {
        logger.info("A method was called: findByAgeBetween");
        return this.studentRepository
                .findByAgeBetween(minAge, maxAge)
                .stream().
                map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Faculty getFaculty(Long id) {
        logger.info("A method was called: getFaculty");
        Student student = this.studentRepository.findById(id).orElseThrow();
        return student.getFaculty();
    }

    public long countStudent() {
        logger.info("A method was called: countStudent");
        return this.studentRepository.countStudent();
    }

    public double getStudentAverageAge() {
        logger.info("A method was called: getStudentAverageAge");
        return this.studentRepository.getStudentAverageAge();
    }

    public Collection<StudentDto> findLastStudent() {
        logger.info("A method was called: findLastStudent");
        return this.studentRepository.findLastStudent();
    }

    public Collection<String> findStudentByLetter() {
        List<String> studentNameList = studentRepository.findAll().stream().map(Student::getName).toList();
        return studentNameList.stream().filter((s -> s.toUpperCase().startsWith("А"))).sorted().collect(Collectors.toList());
    }


    public double getStudentAverageAgeStream() {
        return this.studentRepository.findAll().stream().collect(Collectors.averagingInt(Student::getAge));
    }

    public int getInt() {
        Stream<Integer> sum = Stream.iterate(1, a -> a + 1);
        return sum.parallel().limit(1_000_000).reduce(0, Integer::sum);
    }
}
