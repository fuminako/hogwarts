package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.dto.StudentDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetAllStudent() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class)).
                isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class)).
                isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        StudentDto student = new StudentDto(5L, "Полумна Лавгуд", 14);

        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/", student, String.class)).
                isNotNull();
    }

    @Test
    public void testPutStudent() throws Exception {
        StudentDto testTest = new StudentDto(5L, "Тест Тестов", 16);
        long testId = testTest.getId();
        StudentDto lunaLovegood = new StudentDto(5L, "Полумна Лавгуд", 14);

        this.restTemplate.put("http://localhost:" + port + "/student/" + testId, testTest);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        StudentDto student = new StudentDto(1L, "Test", 15);
        long id = student.getId();

        restTemplate.delete("http://localhost:" + port + "/student/" + id);
    }

    @Test
    public void testFindByAge() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find" + 18, String.class)).
                isNotEmpty();
    }

    @Test
    public void testFindByAgeBetween() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find/between" + 17 + 19, String.class)).
                isNotEmpty();
    }

    @Test
    public void testGetFaculty() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/find_faculty" + 1, String.class)).
                isNotEmpty();
    }

}
