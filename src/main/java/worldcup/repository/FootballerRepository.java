package worldcup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import worldcup.models.Footballer;

import java.util.List;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, Long> {

    @Override
    @NonNull
    @Query("SELECT f FROM Footballer f ORDER BY f.numberOfMatchesForRepresentation DESC")
    List<Footballer> findAll();

    @Query("SELECT f FROM Footballer f WHERE f.yearOfBirth > :yearOfBirth ORDER BY f.yearOfBirth ASC")
    List<Footballer> findByYearOfBirth(@Param("yearOfBirth") Integer yearOfBirth);

    @Query("SELECT f FROM Footballer f WHERE f.numberOfMatchesForRepresentation BETWEEN :start AND :end ORDER BY f.numberOfMatchesForRepresentation ASC")
    List<Footballer> findByMatchesBetween(@Param("start") int start, @Param("end") int end);
}
