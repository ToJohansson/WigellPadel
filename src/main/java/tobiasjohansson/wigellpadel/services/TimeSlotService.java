package tobiasjohansson.wigellpadel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobiasjohansson.wigellpadel.exceptions.ResourceNotFoundException;
import tobiasjohansson.wigellpadel.models.TimeSlot;
import tobiasjohansson.wigellpadel.repositories.TimeSlotRepository;

import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public TimeSlotService() {
    }

    public List<TimeSlot> getCourts() {
        return timeSlotRepository.findAll();
    }

    public TimeSlot getTimeSlotById(long id) throws ResourceNotFoundException {
        return timeSlotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TimeSlot", "ID", id));
    }
    public void saveTimeSlot(TimeSlot timeSlot){
        timeSlotRepository.save(timeSlot);
    }
}
