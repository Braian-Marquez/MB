package spring.edu.Proyecto.Final.service;

import java.util.List;
import java.util.Optional;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import spring.edu.Proyecto.Final.model.Product;
import spring.edu.Proyecto.Final.repository.IProductRepository;
import spring.edu.Proyecto.Final.service.interfaces.IProductService;
@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    private final UploadFileService uploadFileService;

    @Override
    public void create(Product productDTO, MultipartFile photo) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDetails(productDTO.getDetails());
        product.setImage(uploadFileService.save(photo));
        product.setCategory(productDTO.getCategory());
        product.setCommerce(productDTO.getCommerce());


        productRepository.save(product);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product  getById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }


}
