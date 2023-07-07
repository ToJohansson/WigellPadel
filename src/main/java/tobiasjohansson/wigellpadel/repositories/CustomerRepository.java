package tobiasjohansson.wigellpadel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tobiasjohansson.wigellpadel.models.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    
   Customer findCustomerByUsername(String username);
}
