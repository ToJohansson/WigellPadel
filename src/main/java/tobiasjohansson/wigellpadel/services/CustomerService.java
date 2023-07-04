package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.TimeSlot;
import tobiasjohansson.wigellpadel.repositories.CustomerRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            customer.setHolderForId(0);
            return customerRepository.save(customer);

        } else
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

            return "Booking was updated";
        }
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

            return "Booking success";
        } else
            return "time is already booked";
    }

    // DELETE
    public String deleteBookingFromCustomer(long customerId,long bookingId) {
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
            return "The booking was deleted";
        }
        return "Wrong id or does not exist...";
    }

}
