package spring.edu.Proyecto.Final.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.model.Product;


import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    void create(Customer customerDTO);
    void update(Customer customerDTO,MultipartFile photo);
    List<Customer> findAll();
    Customer findById(Integer id);
    Customer save (Customer customer);


}
