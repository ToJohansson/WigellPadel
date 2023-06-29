package tobiasjohansson.wigellpadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tobiasjohansson.wigellpadel.models.TimeSlot;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
}
