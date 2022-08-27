package spring.edu.Proyecto.Final.service.interfaces;

import spring.edu.Proyecto.Final.model.Category;
import spring.edu.Proyecto.Final.model.Product;

import java.util.List;

public interface ICategoryService {

    void create(Category categoryDTO);
    List<Category> getAll();
    void delete(Category categoryDTO);
    void update(Category CategoryDTO);
    void deleteById(Integer id);
    Category getById(Integer id);
    List<Product> findByName(String name);
}
