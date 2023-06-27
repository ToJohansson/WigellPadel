package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Court;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.Slot;
import tobiasjohansson.wigellpadel.repositories.CustomerRepository;
import tobiasjohansson.wigellpadel.repositories.SlotRepository;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CourtService courtService;

    public CustomerService() {
    }

    // LIST
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Booking> getMyBookings(Long id) throws ResourceNotFoundException {
        Customer c = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "ID", id));
        return null;
    }

    // SAVE
    public Customer saveCustomer(Customer customer) {
        if (customer.getHolderForId() > 0) {
            customer.setAddress(addressService.addressById(customer.getHolderForId()));
            customer.setHolderForId(0);
            return customerRepository.save(customer);

        } else
            addressService.saveAddress(customer.getAddress());
        return customerRepository.save(customer);
    }

    public Booking saveBooking(Booking booking) throws ResourceNotFoundException {
        /**
         * TODO:ERROR CHECKS AT THE END
         * TODO: NO CHECK FOR INDEX HOLDER; IF() DID NOT WORK LAST TIME
         * TODO: WHEN BOOKING, ITS ALWAYS CHANGING THE 0 INDEX OF ALL SLOTS
         */
        // check if customer exists
        customerRepository.findById(booking.getCustomerIdHolder()).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "ID", booking.getCustomerIdHolder()));

        if (booking.getCustomerIdHolder() > 0) {

//            booking.addCourt(courtService.getCourtById(booking.getCourtIdHolder()));
//            int lastIndex = booking.getCourt().size() - 1; // get the newest made booking
//            Slot slotToChange = booking.getCourt().get(lastIndex).getSlots().get((int) booking.getSlotIndexHolder() - 1);
//
//            slotToChange.setAvailable(false);
//            slotToChange.setStatus("Booked");

            // TODO NEW CHANGE TO THE BOOKING OVER HERE!!!
            booking.addCourt(courtService.getCourtById(booking.getCourtIdHolder()));
            Court lastCourt = null;
            for (Court element : booking.getCourts()) {
                lastCourt = element;
            }
            Slot slotToChange = lastCourt.getSlots().get((int) booking.getSlotIndexHolder() - 1);

            slotToChange.setAvailable(false);
            slotToChange.setStatus("Booked");

            booking.setDateOfBooking(new Date());
            booking.setCustomerInformation(customerRepository.findById(booking.getCustomerIdHolder()).orElseThrow(() ->
                    new ResourceNotFoundException("Customer", "ID", booking.getCustomerIdHolder())));
            return bookingService.saveBooking(booking);

            // ERROR MESSAGE HERE -------index holder is bigger than size of slots list----------
        }
        return null;
    }
    // UPDATE

    // DELETE

}
