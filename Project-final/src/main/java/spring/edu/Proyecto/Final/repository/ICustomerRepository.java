package spring.edu.Proyecto.Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edu.Proyecto.Final.model.Customer;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

}
