package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Address;
import tobiasjohansson.wigellpadel.repositories.AddressRepository;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressService(){
    }

    // LIST
    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }
    // FIND ADDRESS BY ID
    public Address addressById(Long id) throws ResourceNotFoundException {
        return addressRepository.findById(id).orElseThrow(()->
                                        new ResourceNotFoundException("Adress","ID",id));
    }
    // SAVE
    public Address saveAddress(Address address){
        return addressRepository.save(address);
    }
    // UPDATE

    // DELETE
}
