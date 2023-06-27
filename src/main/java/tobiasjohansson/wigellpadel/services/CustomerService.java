package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceExsistException;
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
    private AddressService addressService;


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
    // FIND CUSTOMER BY ID
    public Customer findCustomerByIdHolder(long id){
        Customer c = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "ID", id));
        return c;
    }

//    public String saveBooking(Booking booking) throws ResourceNotFoundException {
//
//        // check if customer exists
//        Customer customer = findCustomerByIdHolder(booking.getCustomerIdHolder());
//
//        if (booking.getCustomerIdHolder() > 0) {
//
//            String response = bookingService.saveBooking(booking);
//
//            if(!response.contains("not available")){
//
//                System.out.println(response);
//                customer.addBookingList(booking);
//                customerRepository.save(customer);
//            }
//            System.out.println(response);
//            return response;
//        }
//        return "Something went wrong...";
//    }
    // UPDATE

    // DELETE

}
