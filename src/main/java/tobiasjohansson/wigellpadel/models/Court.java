package tobiasjohansson.wigellpadel.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COURTS")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courtId;

    @ManyToMany
    @JoinTable(name = "court_slots",
            joinColumns = @JoinColumn(name = "court_id"),
            inverseJoinColumns = @JoinColumn(name = "slot_id"))
    private List<Slot> slots = new ArrayList<>();

    // SIZE
    // PRICE

    public Court() {
    }

    public long getCourtId() {
        return courtId;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void addSlots(Slot slot) {
        this.slots.add(slot);
    }
}
