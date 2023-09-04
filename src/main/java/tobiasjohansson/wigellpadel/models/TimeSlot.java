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
    private double sek;
    private double euro;

    public TimeSlot() {
    }


    public TimeSlot(String courtName, String time,double sek,double euro ,boolean available) {
        this.courtName = courtName;
        this.time = time;
        this.sek = sek;
        this.euro = euro;
        this.available = available;
    }

    public long getTimeSlotId() {
        return timeSlotId;
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

    public double getSek() {
        return sek;
    }

    public void setSek(double sek) {
        this.sek = sek;
    }

    public double getEuro() {
        return euro;
    }

    public void setEuro(double euro) {
        this.euro = euro;

    }

}
