package tobiasjohansson.wigellpadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tobiasjohansson.wigellpadel.models.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
}
