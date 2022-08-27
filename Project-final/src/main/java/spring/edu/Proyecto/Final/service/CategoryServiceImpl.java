package spring.edu.Proyecto.Final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.edu.Proyecto.Final.model.Category;
import spring.edu.Proyecto.Final.model.Product;
import spring.edu.Proyecto.Final.repository.ICategoryRepository;
import spring.edu.Proyecto.Final.repository.IProductRepository;
import spring.edu.Proyecto.Final.service.interfaces.ICategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;
    IProductRepository productRepository;

    @Override
    @Transactional
    public void create(Category categoryDTO) {

        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        categoryRepository.save(category);
    }
    @Override
    @Transactional
    public void update(Category categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getId()).get();

        category.setName(categoryDTO.getName());

        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getAll() {

        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Category categoryDTO) {
        categoryRepository.delete(categoryDTO);
    }

    @Transactional(readOnly = true)
    public Category getById(Integer id) {

        return categoryRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findByName(String name){
        return productRepository.findByName(name);
    }


}


