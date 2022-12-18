package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.NoStudentInListException;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private long counter = 0L;

    private final Map<Long, Student> studentMap = new HashMap<>();

    public Student addStudent(Student student) {
        long newId = counter++;
        student.setId(newId);
        studentMap.put(newId, student);
        return student;
    }

    public Student updateStudent(Long studentId, Student student) {
        if (this.studentMap.containsKey(studentId)) {
            Student oldStudent = this.studentMap.get(studentId);
            oldStudent.setAge(student.getAge());
            oldStudent.setName(student.getName());
            return oldStudent;
        } else {
            throw new NoStudentInListException("Студент не найден");
        }
    }

    public Collection<Student> getAllStudent() {
        return studentMap.values();
    }

    public Student getStudentById(Long id) {
        if (this.studentMap.containsKey(id)) {
            return studentMap.get(id);
        } else {
            throw new NoStudentInListException("Студент не найден");
        }
    }

    public void deleteStudent(Long id) {
        if (this.studentMap.containsKey(id)) {
            this.studentMap.remove(id);
        } else {
            throw new NoStudentInListException("Студент не найден");
        }
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
