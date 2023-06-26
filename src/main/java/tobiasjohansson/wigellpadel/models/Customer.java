package tobiasjohansson.wigellpadel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 100, nullable = false)
    private String username;

    @OneToMany()
    private List<Booking> myBookingList;
    @ManyToOne()
    private Address address;

   @Transient
    private long holderForId;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String username, long holderForId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.holderForId = holderForId;
    }

    public Customer(String firstName, String lastName, String username, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Booking> getMyBookingList() {
        return myBookingList;
    }

    public void setMyBookingList(List<Booking> myBookingList) {
        this.myBookingList = myBookingList;
    }

    public long getHolderForId() {
        return holderForId;
    }

    public void setHolderForId(long addressIdHolder) {
        this.holderForId = addressIdHolder;
    }
}
