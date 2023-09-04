package tobiasjohansson.wigellpadel.bootsrapData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tobiasjohansson.wigellpadel.models.*;
import tobiasjohansson.wigellpadel.repositories.*;
import tobiasjohansson.wigellpadel.services.TimeSlotService;


@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TimeSlotService timeSlotService;

    @Override
    public void run(String... args) throws Exception {

        // TIDER OCH BANOR
        TimeSlot timeSlot1 = new TimeSlot("Bana 1","1000-1100",250,0,true);
        TimeSlot timeSlot2 = new TimeSlot("Bana 1","1200-1300",350,0,true);
        TimeSlot timeSlot3 = new TimeSlot("Bana 1","1400-1500",450,0,true);

        TimeSlot timeSlot4 = new TimeSlot("Bana 2","1000-1100",250,0,true);
        TimeSlot timeSlot5 = new TimeSlot("Bana 2","1200-1300",350,0,true);
        TimeSlot timeSlot6 = new TimeSlot("Bana 2","1400-1500",450,0,true);

        TimeSlot timeSlot7 = new TimeSlot("Bana 3","1000-1100",250,0,true);
        TimeSlot timeSlot8 = new TimeSlot("Bana 3","1200-1300",350,0,true);
        TimeSlot timeSlot9 = new TimeSlot("Bana 3","1400-1500",450,0,true);

        timeSlotService.saveTimeSlot(timeSlot1);
        timeSlotService.saveTimeSlot(timeSlot2);
        timeSlotService.saveTimeSlot(timeSlot3);
        timeSlotService.saveTimeSlot(timeSlot4);
        timeSlotService.saveTimeSlot(timeSlot5);
        timeSlotService.saveTimeSlot(timeSlot6);
        timeSlotService.saveTimeSlot(timeSlot7);
        timeSlotService.saveTimeSlot(timeSlot8);
        timeSlotService.saveTimeSlot(timeSlot9);

        /*****************************************************************************************
         * CUSTOMERS
         */
        Address address1 = new Address("lyckliga gatan","456192","Umeå");
        addressRepository.save(address1);

        Customer customer1 = new Customer("Tobbe","J","kollkarl",address1);
        customerRepository.save(customer1);

    }
}
