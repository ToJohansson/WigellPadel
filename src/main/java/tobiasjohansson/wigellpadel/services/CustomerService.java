package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.TimeSlot;
import tobiasjohansson.wigellpadel.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressService addressService;
//    @Autowired
//    private TimeSlotService timeSlotService;
//    @Autowired
//    private BookingService bookingService;


    // LIST
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Booking> getMyBookings(long id) {
        Customer customer = findCustomerById(id);

        for (int i = 0; i < customer.getMyBookingList().size(); i++){
            System.out.println(customer.getMyBookingList().get(i).getTimeSlot().getTime());
        }

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
    public Customer updateCustomer(Customer customer, Long id) {
        Customer customerToUpdate = findCustomerById(id);

        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setUsername(customer.getUsername());
        customerToUpdate.setAddress(customer.getAddress());

        return customerRepository.save(customerToUpdate);
    }

    public void saveBookingToCustomer(long id,Booking booking){
        Customer customer = findCustomerById(id);
        customer.addBookingList(booking);
        customerRepository.save(customer);
    }

//    public String saveBooking(long timeId, long customerId) {
//        TimeSlot timeSlot = timeSlotService.getTimeSlotById(timeId);
//        Customer customer = findCustomerById(customerId);
//
//        Booking booking = new Booking(timeSlot, customer);
//
//        bookingService.saveBooking(booking);
//
//        customer.addBookingList(booking);
//        customerRepository.save(customer);
//
//        return "Booking success";
//    }

    // DELETE

}
