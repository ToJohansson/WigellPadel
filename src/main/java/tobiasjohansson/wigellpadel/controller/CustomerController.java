package tobiasjohansson.wigellpadel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tobiasjohansson.wigellpadel.models.Booking;
import tobiasjohansson.wigellpadel.models.TimeSlot;
import tobiasjohansson.wigellpadel.services.CustomerService;
import tobiasjohansson.wigellpadel.services.TimeSlotService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v5/")
public class CustomerController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private TimeSlotService timeSlotService;


    @GetMapping("/availability")
    public List<TimeSlot> getAllCourts() {
        return timeSlotService.getCourts();
    }

    @GetMapping("/mybookings/{id}")
    public ResponseEntity<List<Booking>> getMyBookings(@PathVariable("id") long id) {

        return ResponseEntity.ok(customerService.getMyBookings(id));
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> bookSlot(@RequestBody Map<String, Long> requestBody) {
        long timeId = requestBody.get("timeId");
        long customerId = requestBody.get("customerId");
        long players = requestBody.get("players");

        return new ResponseEntity<String>(customerService.saveBooking(timeId, customerId, players), HttpStatus.OK);
    }

}
