package tobiasjohansson.wigellpadel.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    @Column(name = "EURO")
    private double euro;
    @Column(name = "PLAYERS")

    private int players;
    @Column(name = "DATE_OF_BOOKING")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateOfBooking;

    @OneToOne(cascade = CascadeType.MERGE)
    private TimeSlot timeSlot;

    public Booking(int players, Date dateOfBooking, TimeSlot timeSlot) {
        this.bookingId = bookingId;
        this.euro = euro;
        this.players = players;
        this.dateOfBooking = dateOfBooking;
        this.timeSlot = timeSlot;
    }

    public Booking(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }


    public Booking() {
    }

    public long getBookingId() {
        return bookingId;
    }

    public double geteuro() {
        return euro;
    }

    public void seteuro(double totalPrice) {
        this.euro = euro;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }


    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
