package worldcup.repo_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import worldcup.models.Footballer;
import worldcup.models.Representation;
import worldcup.repository.FootballerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FootballerRepositoryTest {

    @Autowired
    private FootballerRepository footballerRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Representation representation;

    @BeforeEach
    void setUp() {
        footballerRepository.deleteAll();

        representation = new Representation();
        representation.setName("Serbia");
        entityManager.persist(representation);

        Footballer f1 = new Footballer("Player1", 1985, 50, representation);
        Footballer f2 = new Footballer("Player2", 1990, 70, representation);
        Footballer f3 = new Footballer("Player3", 2000, 30, representation);

        entityManager.persist(f1);
        entityManager.persist(f2);
        entityManager.persist(f3);
        entityManager.flush();
    }

    @Test
    void shouldReturnAllFootballersOrderedByMatches() {
        List<Footballer> result = footballerRepository.findAll();

        assertEquals(3, result.size());
        assertEquals("Player2", result.get(0).getFullName()); // Najviše mečeva
        assertEquals("Player1", result.get(1).getFullName());
        assertEquals("Player3", result.get(2).getFullName()); // Najmanje mečeva
    }

    @Test
    void shouldReturnFootballersBornAfterYear() {
        List<Footballer> result = footballerRepository.findByYearOfBirth(1988);

        assertEquals(2, result.size());
        assertEquals("Player2", result.get(0).getFullName()); // Rođen 1990
        assertEquals("Player3", result.get(1).getFullName()); // Rođen 2000
    }

    @Test
    void shouldReturnFootballersWithinMatchesRange() {
        List<Footballer> result = footballerRepository.findByMatchesBetween(30, 70);

        assertEquals(3, result.size());
        assertEquals("Player3", result.get(0).getFullName()); // 30 mečeva
        assertEquals("Player1", result.get(1).getFullName()); // 50 mečeva
        assertEquals("Player2", result.get(2).getFullName()); // 70 mečeva
    }
}