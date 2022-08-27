package spring.edu.Proyecto.Final.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import spring.edu.Proyecto.Final.model.Product;

import java.util.List;


public interface IProductService {
    void create(Product product, MultipartFile photo);
    void save( Product product);
    void delete(Integer id);
    List<Product> findAll();
}
