package tobiasjohansson.wigellpadel.models;

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
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Court> court = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_court",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "court_id"))
    private Set<Court> courts = new HashSet<>();
    @ManyToOne()
    private Customer customerInformation;
    @Transient
    private long courtIdHolder;
    @Transient
    private int slotIndexHolder;
    @Transient
    private long customerIdHolder;

    public Booking( long customerIdHolder, long courtIdHolder, int slotIndexHolder, int players) {
        this.customerIdHolder = customerIdHolder;
        this.courtIdHolder = courtIdHolder;
        this.slotIndexHolder = slotIndexHolder;
        this.players = players;

    }

    public Booking(){}

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

    public long getCourtIdHolder() {
        return courtIdHolder;
    }

    public Customer getCustomerInformation() {
        return customerInformation;
    }

    public void setCustomerInformation(Customer customer) {
        this.customerInformation = customer;
    }

    public void setCourtIdHolder(long courtIdHolder) {
        this.courtIdHolder = courtIdHolder;
    }

    public long getSlotIndexHolder() {
        return slotIndexHolder;
    }

//    public void addCourt(Court court){
//        this.court.add(court);
//    }
//    public List<Court> getCourt() {
//        return court;
//    }
//
//    public void setCourt(List<Court> court) {
//        this.court = court;
//    }
    public void addCourt(Court court) {
        courts.add(court);
    }

    public Set<Court> getCourts() {
        return courts;
    }

    public void setCourts(Set<Court> courts) {
        this.courts = courts;
    }

    public void setSlotIndexHolder(int slotIndexHolder) {
        this.slotIndexHolder = slotIndexHolder;
    }

    public long getCustomerIdHolder() {
        return customerIdHolder;
    }

    public void setCustomerIdHolder(long customerIdHolder) {
        this.customerIdHolder = customerIdHolder;
    }
}
