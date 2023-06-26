package tobiasjohansson.wigellpadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tobiasjohansson.wigellpadel.models.Court;
@Repository
public interface CourtRepository extends JpaRepository<Court,Long> {
}
