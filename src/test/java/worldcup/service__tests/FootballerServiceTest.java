package worldcup.service__tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import worldcup.models.Footballer;
import worldcup.models.Representation;
import worldcup.repository.FootballerRepository;
import worldcup.service.impl.JpaFootballerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FootballerServiceTest {

    @Mock
    private FootballerRepository repository;

    @InjectMocks
    private JpaFootballerService service;

    @Test
    void testFindAllFootballers() {
        List<Footballer> footballers = List.of(new Footballer("Lionel Messi", 1987, 180, null));
        when(repository.findAll()).thenReturn(footballers);

        List<Footballer> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("Lionel Messi", result.get(0).getFullName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnFootballersWithinMatchesRange() {
        List<Footballer> footballers = List.of(
                new Footballer("Player1", 1987, 50, null),
                new Footballer("Player2", 1990, 30, null),
                new Footballer("Player3", 1995, 70, new Representation(
                        "National League A", "NLA", 1950, 7
                ))
        );
        when(repository.findByMatchesBetween(30, 70)).thenReturn(footballers);

        List<Footballer> result = service.findByMatchesBetween(30, 70);
        assertEquals(3, result.size());
        assertEquals(1950, result.get(2).getRepresentation().getEstablishmentYear());
        assertNull(result.get(1).getRepresentation());
        verify(repository, times(1)).findByMatchesBetween(30, 70);
    }
}
