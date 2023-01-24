SELECT s.name, s.age, f.name
FROM student s
         LEFT JOIN faculty f on f.id = s.faculty_id;

SELECT s.*
FROM student s
         INNER JOIN faculty f on f.id = s.faculty_id;