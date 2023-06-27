package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Court;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.Slot;
import tobiasjohansson.wigellpadel.repositories.BookingRepository;
import tobiasjohansson.wigellpadel.repositories.CustomerRepository;
import tobiasjohansson.wigellpadel.repositories.SlotRepository;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CourtService courtService;
    @Autowired
    private CustomerService customerService;

    public BookingService() {
    }

    // LIST
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    // SAVE

    public String saveBooking(Booking booking) throws ResourceNotFoundException {

        // check if customer exists
        Customer customerInfo = customerService.findCustomerByIdHolder(booking.getCustomerIdHolder());

        if(customerInfo != null) {

            booking.addCourt(courtService.getCourtById(booking.getCourtIdHolder()));
            Court lastCourt = null;
            for (Court element : booking.getCourts()) {
                lastCourt = element;
            }

            Slot slotToChange = lastCourt.getSlots().get((int) booking.getSlotIndexHolder() - 1);

            if (slotToChange.isAvailable()) {
                slotToChange.setAvailable(false);
                slotToChange.setStatus("Booked");

                booking.setDateOfBooking(new Date());
                booking.setCustomerInformation(customerInfo);

                bookingRepository.save(booking);

                customerInfo.addBookingList(booking);
                customerService.saveCustomer(customerInfo);

                return "Your booking was successful, time:" + slotToChange.getTimeSlot() + " , at court " + lastCourt.getCourtId() + ".";
            } else {
                return "Timeslot is not available at court " + lastCourt.getCourtId() + ", Time:" + slotToChange.getTimeSlot() + " is already booked.";
            }
        } return "Customer does not exist";
    }

    // UPDATE

    // DELETE
}
