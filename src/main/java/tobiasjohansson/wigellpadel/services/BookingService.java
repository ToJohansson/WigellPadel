package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.repositories.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TimeSlotService timeSlotService;

    public BookingService() {
    }

    // LIST
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking findBookingById(long id)throws ResourceNotFoundException {
        return bookingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking","ID",id));
    }

//     SAVE
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    // UPDATE
    public Booking updateBooking(Booking updatedBooking) {
        Booking existingBooking = findBookingById(updatedBooking.getBookingId());

        existingBooking.setTimeSlot(updatedBooking.getTimeSlot());
        existingBooking.getTimeSlot().setAvailable(false);
        return bookingRepository.save(existingBooking);
    }
    // DELETE
    public void deleteBooking(long id){
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(optionalBooking.isPresent()){
            bookingRepository.deleteById(id);
        }
    }

}
