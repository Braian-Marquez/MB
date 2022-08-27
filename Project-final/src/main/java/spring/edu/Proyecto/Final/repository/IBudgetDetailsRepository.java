package spring.edu.Proyecto.Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edu.Proyecto.Final.model.BudgetDetails;


@Repository
public interface IBudgetDetailsRepository extends JpaRepository<BudgetDetails, Integer> {

}
