package tobiasjohansson.wigellpadel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.services.BookingService;
import tobiasjohansson.wigellpadel.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v5/")
public class AdminController {

    /**

     *
     * • Ta bort en bokning
     * DELETE /api/v5/deletebooking/{id}
     *
     * • Uppdatera information
     * PUT /api/v5/updateinfo
     *
     * • Uppdatera bokning
     * PUT /api/v5/bookings/{id}
     */
    @Autowired
    private CustomerService customerService;


    public AdminController(){}

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @PostMapping("/register")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }
    @DeleteMapping("/deletebooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") long id) {
        customerService.deleteBookingFromCustomer(id);
        return new ResponseEntity<String>("Booking was deleted",HttpStatus.OK);

    }
}
