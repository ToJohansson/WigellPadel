package tobiasjohansson.wigellpadel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import java.util.ArrayList;
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
    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> myBookingList = new ArrayList<>();
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
        return this.myBookingList;
    }

    public long getHolderForId() {
        return holderForId;
    }

    public void setHolderForId(long addressIdHolder) {
        this.holderForId = addressIdHolder;
    }

    public void addBookingList(Booking booking) {
        this.myBookingList.add(booking);
    }
}
