package worldcup.repo_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import worldcup.models.Footballer;
import worldcup.models.Representation;
import worldcup.repository.RepresentationRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepresentationRepositoryTest {
    private final RepresentationRepository repository;
    private final TestEntityManager entityManager;

    @Autowired
    public RepresentationRepositoryTest(RepresentationRepository repository, TestEntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        Representation representation = new Representation("National League A", "NLA", 1977, 4);
        Representation representation1 = new Representation("National League B", "NLB", 1975, 6);
        Footballer f1 = new Footballer("Player1", 1985, 50, representation);
        Footballer f2 = new Footballer("Player2", 1990, 70, representation);
        Footballer f3 = new Footballer("Player3", 2000, 30, representation);

        entityManager.persist(f1);
        entityManager.persist(f2);
        entityManager.persist(f3);

        representation.getFootballers().addAll(List.of(f1, f2, f3));

        entityManager.persist(representation);
        entityManager.persist(representation1);
        entityManager.flush();
    }

    @Test
    void shouldFindTop2ByOrderByTitlesDesc() {
        List<Representation> result = repository.findTop2ByOrderByTitlesDesc();

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindThreeFootballersInRepresentationList() {
        List<Representation> representations = repository.findTop2ByOrderByTitlesDesc();
        List<Footballer> result = representations.get(1).getFootballers();

        assertEquals(2, representations.size());
        assertEquals(3, result.size());
    }

    @Test
    void shouldNotFindFootballersInRepresentationList() {
        List<Representation> representations = repository.findTop2ByOrderByTitlesDesc();
        List<Footballer> result = representations.get(0).getFootballers();

        assertEquals(2, representations.size());
        assertEquals(0, result.size());
    }
}
