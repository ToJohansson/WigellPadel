package tobiasjohansson.wigellpadel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.Court;
import tobiasjohansson.wigellpadel.services.BookingService;
import tobiasjohansson.wigellpadel.services.CourtService;
import tobiasjohansson.wigellpadel.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v5/")
public class CustomerController {

    @Autowired
    private CourtService courtService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookingService bookingService;

    public CustomerController(){
    }

    @GetMapping("/availability")
    public List<Court> getAllCourts(){
        return courtService.getCourts();
    }
    @GetMapping("/mybookings/{id}")
    public List<Booking> getMyBookings(@PathVariable("id") Long id){
        return customerService.getMyBookings(id);
    }
    @PostMapping("/bookings")
    public ResponseEntity<String> bookSlot(@RequestBody Booking booking){
        return new ResponseEntity<String>(bookingService.saveBooking(booking), HttpStatus.OK);
    }
}
