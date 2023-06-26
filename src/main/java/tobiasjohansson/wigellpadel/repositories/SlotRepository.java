package tobiasjohansson.wigellpadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tobiasjohansson.wigellpadel.models.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Long> {
}
