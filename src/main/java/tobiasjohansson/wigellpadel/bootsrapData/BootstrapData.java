package tobiasjohansson.wigellpadel.bootsrapData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tobiasjohansson.wigellpadel.models.*;
import tobiasjohansson.wigellpadel.repositories.*;

import java.util.Date;


@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;


    @Override
    public void run(String... args) throws Exception {

        // TIDER OCH BANOR
        TimeSlot timeSlot1 = new TimeSlot("Bana 1","1000-1100",true);
        TimeSlot timeSlot2 = new TimeSlot("Bana 1","1200-1300",true);
        TimeSlot timeSlot3 = new TimeSlot("Bana 1","1400-1500",true);

        TimeSlot timeSlot4 = new TimeSlot("Bana 2","1000-1100",true);
        TimeSlot timeSlot5 = new TimeSlot("Bana 2","1200-1300",true);
        TimeSlot timeSlot6 = new TimeSlot("Bana 2","1400-1500",true);

        TimeSlot timeSlot7 = new TimeSlot("Bana 3","1000-1100",true);
        TimeSlot timeSlot8 = new TimeSlot("Bana 3","1200-1300",true);
        TimeSlot timeSlot9 = new TimeSlot("Bana 3","1400-1500",true);

        timeSlotRepository.save(timeSlot1);
        timeSlotRepository.save(timeSlot2);
        timeSlotRepository.save(timeSlot3);
        timeSlotRepository.save(timeSlot4);
        timeSlotRepository.save(timeSlot5);
        timeSlotRepository.save(timeSlot6);
        timeSlotRepository.save(timeSlot7);
        timeSlotRepository.save(timeSlot8);
        timeSlotRepository.save(timeSlot9);

        /*****************************************************************************************
         * CUSTOMERS
         */
        Address address1 = new Address("lyckliga gatan","456192","Umeå");
        addressRepository.save(address1);

        Customer customer1 = new Customer("Tobbe","J","kollkarl",address1);
        customerRepository.save(customer1);

        /**
         * ***********************************
         * BOOKING
         */
        Booking booking1 = new Booking(timeSlot1);
        Booking booking2 = new Booking(timeSlot2);
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
        customer1.addBookingList(booking1);
        customer1.addBookingList(booking2);
        customerRepository.save(customer1);
    }
}
