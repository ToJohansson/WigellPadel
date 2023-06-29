package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.TimeSlot;
import tobiasjohansson.wigellpadel.repositories.BookingRepository;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private CustomerService customerService;
    public BookingService() {
    }

    // LIST
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    // SAVE
//    public void saveBooking(Booking booking) {
//        bookingRepository.save(booking);
//    }
    public String saveBooking(long timeId, long customerId) {
        TimeSlot timeSlot = timeSlotService.getTimeSlotById(timeId);

        Customer customer = customerService.findCustomerById(customerId);

        Booking booking = new Booking(timeSlot, customer);

        bookingRepository.save(booking);

        customerService.saveBookingToCustomer(customerId,booking);

        return "Booking success";
    }
    // UPDATE

    // DELETE
}
