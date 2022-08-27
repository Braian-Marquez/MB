

package spring.edu.Proyecto.Final.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.repository.ICustomerRepository;
import spring.edu.Proyecto.Final.service.interfaces.ICustomerService;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    private final UploadFileService uploadFileService;

    @Transactional
    public void create(Customer customerDTO) {


        Customer customer = new Customer();
        customer.setDni(customerDTO.getDni());
        customer.setName(customerDTO.getName());
        customer.setLocation(customerDTO.getLocation());
        customer.setSurname(customerDTO.getSurname());
        customer.setTelephone(customerDTO.getTelephone());


        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }


    @Transactional
    public void update(Customer customerDTO, MultipartFile photo) {
        Customer customer = customerRepository.findById(customerDTO.getId()).get();

        customer.setDni(customerDTO.getDni());
        customer.setName(customerDTO.getName());
        customer.setPhoto(uploadFileService.save(photo));
        customer.setCountry(customerDTO.getCountry());
        customer.setState(customerDTO.getState());
        customer.setZipCode(customerDTO.getZipCode());
        customer.setLocation(customerDTO.getLocation());
        customer.setSurname(customerDTO.getSurname());
        customer.setTelephone(customerDTO.getTelephone());

        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAll() {

        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getById(Integer id) {

        return customerRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }
}


