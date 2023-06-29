package tobiasjohansson.wigellpadel.models;

import jakarta.persistence.*;


@Entity
@Table(name = "TIME_SLOTS")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long timeSlotId;

    private String courtName;
    private String time;
    private boolean available;
    private double price;

    public TimeSlot() {
    }

    public TimeSlot(String courtName, String time,double price, boolean available) {
        this.courtName = courtName;
        this.time = time;
        this.price = price;
        this.available = available;
    }

    public long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
