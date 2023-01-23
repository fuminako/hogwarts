package ru.hogwarts.school.exceptions;

public class NoStudentInListException extends RuntimeException {
    public NoStudentInListException(String massage) {
        super(massage);
    }
}
