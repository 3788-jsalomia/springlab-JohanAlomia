package edu.espe.springlab.repository;


import edu.espe.springlab.domain.Student;
import edu.espe.springlab.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository respository;

    @Test
    void shouldSaveAndFineStudentByEmail(){
        //Nombre: Johan Alomia
        Student s = new Student();
        s.setFullName("Test User");
        s.setEmail("test@example.com");
        s.setBirthDate(LocalDate.of(2000,10,10));
        s.setActive(true);

        respository.save(s);

        var result = respository.findByEmail("test@example.com");

        assertThat(result).isPresent();
        assertThat((result).get().getFullName()).isEqualTo("Test User");

    }



    // Test de controlador (MockMvc)
    /*

    @Test
    void shouldDeactivateStudent() {
        Student s = new Student("Ana", "ana@mail.com", LocalDate.of(2000,1,1), true);
        repository.save(s);

        service.deactivateStudent(s.getId());

        Student result = repository.findById(s.getId()).get();
        assertThat(result.isActive()).isFalse();
        assertThat(result.getFullName()).isEqualTo("Ana");
        assertThat(result.getEmail()).isEqualTo("ana@mail.com");
    }
    @Test
    void shouldReturnCorrectStats() {
        repository.save(new Student("Ana", "ana@mail.com", LocalDate.now(), true));
        repository.save(new Student("Juan", "juan@mail.com", LocalDate.now(), true));
        repository.save(new Student("Pedro", "pedro@mail.com", LocalDate.now(), false));

        StatsResponse stats = service.getStats();

        assertThat(stats.getTotal()).isEqualTo(3);
        assertThat(stats.getActive()).isEqualTo(2);
        assertThat(stats.getInactive()).isEqualTo(1);
    }
    @Test
    void shouldAddXElapsedTimeHeader() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Elapsed-Time"))
                .andExpect(header().string("X-Elapsed-Time", not(isEmptyOrNullString())));
    }
    @Test
    void shouldFindByPartialName() {
        repository.save(new Student("Ana", "ana@mail.com", LocalDate.now(), true));
        repository.save(new Student("Andrea", "andrea@mail.com", LocalDate.now(), true));
        repository.save(new Student("Juan", "juan@mail.com", LocalDate.now(), true));

        List<Student> result = repository.findByFullNameContainingIgnoreCase("an");

        assertThat(result).extracting(Student::getFullName)
                .containsExactlyInAnyOrder("Ana", "Andrea")
                .doesNotContain("Juan");
    }*/

}
