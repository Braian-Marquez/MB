package spring.edu.Proyecto.Final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.edu.Proyecto.Final.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
            
    /*
    @Query(value = "select p.name, p.price, c.name from product p INNER JOIN categories c ON" +
            " p.id = c.id")
            List<Product> findProductByCategoryName(String categoryName);
             */


}
