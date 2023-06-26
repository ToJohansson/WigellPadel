package tobiasjohansson.wigellpadel.bootsrapData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tobiasjohansson.wigellpadel.models.Address;
import tobiasjohansson.wigellpadel.models.Court;
import tobiasjohansson.wigellpadel.models.Customer;
import tobiasjohansson.wigellpadel.models.Slot;
import tobiasjohansson.wigellpadel.repositories.AddressRepository;
import tobiasjohansson.wigellpadel.repositories.CourtRepository;
import tobiasjohansson.wigellpadel.repositories.CustomerRepository;
import tobiasjohansson.wigellpadel.repositories.SlotRepository;


@Component
public class BootstrapData implements CommandLineRunner {


    @Autowired
    private CourtRepository courtRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {

        // TIDER OCH BANOR
        Slot court1Slot1 = new Slot("0900-1000",true,"Available",250);
        Slot court1Slot2 = new Slot("1000-1100",true,"Available",300);
        Slot court1Slot3 = new Slot("1300-1400",true,"Available",400);
        Slot court1Slot4 = new Slot("1500-1600",true,"Available",450);
        Slot court1Slot5 = new Slot("1700-1800",true,"Available",550);
        slotRepository.save(court1Slot1);
        slotRepository.save(court1Slot2);
        slotRepository.save(court1Slot3);
        slotRepository.save(court1Slot4);
        slotRepository.save(court1Slot5);

        Slot court2Slot1 = new Slot("0900-1000",true,"Available",250);
        Slot court2Slot2 = new Slot("1000-1100",true,"Available",300);
        Slot court2Slot3 = new Slot("1300-1400",true,"Available",400);
        Slot court2Slot4 = new Slot("1500-1600",true,"Available",450);
        Slot court2Slot5 = new Slot("1700-1800",true,"Available",550);
        slotRepository.save(court2Slot1);
        slotRepository.save(court2Slot2);
        slotRepository.save(court2Slot3);
        slotRepository.save(court2Slot4);
        slotRepository.save(court2Slot5);

        Slot court3Slot1 = new Slot("0900-1000",true,"Available",250);
        Slot court3Slot2 = new Slot("1000-1100",true,"Available",300);
        Slot court3Slot3 = new Slot("1300-1400",true,"Available",400);
        Slot court3Slot4 = new Slot("1500-1600",true,"Available",450);
        Slot court3Slot5 = new Slot("1700-1800",true,"Available",550);
        slotRepository.save(court3Slot1);
        slotRepository.save(court3Slot2);
        slotRepository.save(court3Slot3);
        slotRepository.save(court3Slot4);
        slotRepository.save(court3Slot5);

        Court bana1 = new Court();
        bana1.addSlots(court1Slot1);
        bana1.addSlots(court1Slot2);
        bana1.addSlots(court1Slot3);
        bana1.addSlots(court1Slot4);
        bana1.addSlots(court1Slot5);
        courtRepository.save(bana1);

        Court bana2 = new Court();
        bana2.addSlots(court2Slot1);
        bana2.addSlots(court2Slot2);
        bana2.addSlots(court2Slot3);
        bana2.addSlots(court2Slot4);
        bana2.addSlots(court2Slot5);
        courtRepository.save(bana2);

        Court bana3 = new Court();
        bana3.addSlots(court3Slot1);
        bana3.addSlots(court3Slot2);
        bana3.addSlots(court3Slot3);
        bana3.addSlots(court3Slot4);
        bana3.addSlots(court3Slot5);
        courtRepository.save(bana3);

        /*****************************************************************************************
         * CUSTOMERS
         */
        Address address1 = new Address("lyckliga gatan","456192","Umeå");
        addressRepository.save(address1);

        Customer customer1 = new Customer("Tobbe","J","kollkarl",address1);
        customerRepository.save(customer1);


    }
}
