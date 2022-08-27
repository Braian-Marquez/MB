package spring.edu.Proyecto.Final.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edu.Proyecto.Final.model.Budget;
import spring.edu.Proyecto.Final.model.Customer;


@Repository
public interface IBudgetRepository extends JpaRepository<Budget, Integer> {


    List<Budget> findByCustomer(Customer customer);
}
