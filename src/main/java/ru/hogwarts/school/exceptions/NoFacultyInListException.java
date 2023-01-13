package ru.hogwarts.school.exceptions;

public class NoFacultyInListException extends RuntimeException {
    public NoFacultyInListException(String massage) {
        super(massage);
    }
}
