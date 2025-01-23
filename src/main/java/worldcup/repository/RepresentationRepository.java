package worldcup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import worldcup.models.Representation;

import java.util.List;

@Repository
public interface RepresentationRepository extends JpaRepository<Representation, Long> {

    @Query("SELECT r FROM Representation r ORDER BY r.titles DESC LIMIT 2")
    List<Representation> findTop2ByOrderByTitlesDesc();
}
