package spring.edu.Proyecto.Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.edu.Proyecto.Final.model.Commerce;

import java.io.Serializable;

@Repository
public interface ICommerceRepository extends JpaRepository<Commerce, Integer> {
}
