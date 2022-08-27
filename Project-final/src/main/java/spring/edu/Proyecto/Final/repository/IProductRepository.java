package spring.edu.Proyecto.Final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.edu.Proyecto.Final.model.Product;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select * from Product where Product.name = %:name%", nativeQuery = true)
    List<Product> findByName(@Param("name") String name);

}
