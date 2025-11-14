package edu.espe.springlab.repository;

import edu.espe.springlab.domain.Student;
import edu.espe.springlab.dto.StatsResponse;
import edu.espe.springlab.repository.StudentRepository;
import edu.espe.springlab.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentService service;

    @Test
    void shouldSaveAndFindStudentByEmail() {
        //Nombre: Johan Alomia
        Student s = new Student();
        s.setFullName("Test User");
        s.setEmail("test@example.com");
        s.setBirthDate(LocalDate.of(2000,10,10));
        s.setActive(true);

        repository.save(s);

        var result = repository.findByEmail("test@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getFullName()).isEqualTo("Test User");
    }

    @Test
    void DesactivarEstudiante() {
        //Nombre: Johan Alomia
        Student s = new Student();
        s.setFullName("Estudiante Activo");
        s.setEmail("estudianteActivo@example.com");
        s.setBirthDate(LocalDate.of(2000,10,10));
        s.setActive(true);
        repository.save(s);

        service.deactivate(s.getId());

        Student result = repository.findById(s.getId()).get();
        assertThat(result.getActive()).isFalse();
        assertThat(result.getFullName()).isEqualTo("Estudiante Activo");
        assertThat(result.getEmail()).isEqualTo("estudianteActivo@example.com");
    }

    @Test
    void Estadisticas() {
        //Nombre: Johan Alomia
        Student s1 = new Student();
        s1.setFullName("Juan");
        s1.setEmail("juan@email.com");
        s1.setBirthDate(LocalDate.of(2000,10,10));
        s1.setActive(true);
        repository.save(s1);

        Student s2 = new Student();
        s2.setFullName("Pepe");
        s2.setEmail("pepe@email.com");
        s2.setBirthDate(LocalDate.of(2000,10,10));
        s2.setActive(true);
        repository.save(s2);

        Student s3 = new Student();
        s3.setFullName("Johan");
        s3.setEmail("johan@email.com");
        s3.setBirthDate(LocalDate.of(2000,10,10));
        s3.setActive(false);
        repository.save(s3);

        StatsResponse stats = service.getStats();

        assertThat(stats.getTotal()).isEqualTo(3);
        assertThat(stats.getActive()).isEqualTo(2);
        assertThat(stats.getInactive()).isEqualTo(1);
    }

    @Test
    void shouldFindByPartialName() {
        //Nombre: Johan Alomia
        Student s1 = new Student();
        s1.setFullName("Juan");
        s1.setEmail("juan@email.com");
        s1.setBirthDate(LocalDate.of(2000,10,10));
        s1.setActive(true);
        repository.save(s1);

        Student s2 = new Student();
        s2.setFullName("Ana");
        s2.setEmail("ana@email.com");
        s2.setBirthDate(LocalDate.of(2000,10,10));
        s2.setActive(true);
        repository.save(s2);

        Student s3 = new Student();
        s3.setFullName("Andrea");
        s3.setEmail("andrea@email.com");
        s3.setBirthDate(LocalDate.of(2000,10,10));
        s3.setActive(true);
        repository.save(s3);

        List<Student> result = repository.findByFullNameContainingIgnoreCase("an");

        assertThat(result).extracting(Student::getFullName)
                .containsExactlyInAnyOrder("Ana", "Andrea")
                .doesNotContain("Juan");
    }
}
