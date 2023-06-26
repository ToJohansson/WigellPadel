package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.Court;
import tobiasjohansson.wigellpadel.repositories.CourtRepository;

import java.util.List;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    public CourtService(){}

    // LIST
    public List<Court> getCourts(){
        return courtRepository.findAll();
    }

    // GET COURT BY ID
    public Court getCourtById(long id) throws ResourceNotFoundException {
        return courtRepository.findById(id).orElseThrow(()->
                                new ResourceNotFoundException("Court","ID",id));
    }
    // SAVE
    public Court saveCourt(Court court){
        return courtRepository.save(court);
    }
    // UPDATE

    // DELETE

}
