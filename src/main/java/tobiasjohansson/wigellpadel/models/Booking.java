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
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;
    @Column(name = "PLAYERS")

    private int players;
    @Column(name = "DATE_OF_BOOKING")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateOfBooking;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne(cascade = CascadeType.MERGE)
    private TimeSlot timeSlot;

    private int indexForTime;
    private long timeId;
    private long customerIdHolder;

    // TODO FIX CONSTRUCTOR ---------------------------------------------------

    public Booking(TimeSlot timeSlot,Customer customer) {
        this.customer= customer;
        this.timeSlot = timeSlot;
    }


    public Booking() {
    }

    public long getBookingId() {
        return bookingId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
    public Customer getCustomerInformation() {
        return customer;
    }

    public void setCustomerInformation(Customer customer) {
        this.customer= customer;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
