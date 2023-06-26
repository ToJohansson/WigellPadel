package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.repositories.BookingRepository;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public BookingService(){}

    // LIST
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    // SAVE
    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    // UPDATE

    // DELETE
}
