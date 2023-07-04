package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.logging.Log4j;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.TimeSlot;
import tobiasjohansson.wigellpadel.repositories.CustomerRepository;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private BookingService bookingService;

    // LIST
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Booking> getMyBookings(long id) {
        Customer customer = findCustomerById(id);
        return customer.getMyBookingList();
    }

    // SAVE
    public Customer saveCustomer(Customer customer) {
        if (customer.getHolderForId() > 0) {
            customer.setAddress(addressService.addressById(customer.getHolderForId()));

            // TODO: check if username already exists!

            Log4j.logger.info("[Customer Created] Username: " + customer.getUsername() +", -- new address added with id:" + customer.getHolderForId());
            return customerRepository.save(customer);

        } else
            Log4j.logger.info("[Customer Created] Username: " + customer.getUsername());
        addressService.saveAddress(customer.getAddress());
        return customerRepository.save(customer);
    }

    // FIND CUSTOMER BY ID
    public Customer findCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "ID", id));
    }

    // UPDATE
    public String updateBooking(long id, long bookingId, long timeId) {
        Customer customerToUpdate = findCustomerById(id);

        Booking bookingToUpdate = null;
        List<Booking> bookingList = customerToUpdate.getMyBookingList();
        for (Booking booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                bookingToUpdate = booking;
                break;
            }
        }

        if (bookingToUpdate != null) {
            bookingToUpdate.getTimeSlot().setAvailable(true);
            timeSlotService.saveTimeSlot(bookingToUpdate.getTimeSlot());
            TimeSlot updatedTimeSlot = timeSlotService.getTimeSlotById(timeId);
            bookingToUpdate.setTimeSlot(updatedTimeSlot);
            bookingService.updateBooking(bookingToUpdate);

            customerRepository.save(customerToUpdate);

            Log4j.logger.info("[Booking Updated] Booking with id:" + bookingId + " was updated");
            return "Booking was updated";
        }
        Log4j.logger.info("[Update Failed] Booking with id:" + bookingId + " was NOT updated");
        return "Failed to update booking";
    }

    public String saveBooking(long timeId, long customerId, long players) {

        TimeSlot timeSlot = timeSlotService.getTimeSlotById(timeId);
        if (timeSlot.isAvailable()) {
            timeSlot.setAvailable(false);

            Customer customer = findCustomerById(customerId);

            Booking booking = new Booking(timeSlot);
            booking.setDateOfBooking(new Date());
            booking.setPlayers((int) players);
            bookingService.saveBooking(booking);

            customer.addBookingList(booking);
            customerRepository.save(customer);

            Log4j.logger.info("[Booking Created] customer with id:" + customerId + ", timeslot with id:" + timeId);

            return "Booking success";
        } else
            Log4j.logger.info("[Booking Failed] customer with id:" + customerId + ", timeslot with id:" + timeId);
        return "time is already booked";
    }

    // DELETE
    public String deleteBookingFromCustomer(long customerId, long bookingId) {
        Customer customer = findCustomerById(customerId);

        List<Booking> bookingList = customer.getMyBookingList();
        Booking bookingToRemove = null;

        for (Booking booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                bookingToRemove = booking;
                break;
            }
        }
        if (bookingToRemove != null) {

            TimeSlot timeSlot = bookingToRemove.getTimeSlot();
            timeSlot.setAvailable(true);
            timeSlotService.saveTimeSlot(timeSlot);

            bookingList.remove(bookingToRemove);
            customerRepository.save(customer);

            bookingService.deleteBooking(bookingId);

            Log4j.logger.info("[Deleted] Booking with id:" + bookingId + ", from customer with id:" + customerId);
            return "The booking was deleted";
        }
        Log4j.logger.info("[Delete Failed] Booking with id:" + bookingId + ", from customer with id:" + customerId);
        return "Wrong id or does not exist...";
    }

}
