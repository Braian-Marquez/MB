package spring.edu.Proyecto.Final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.edu.Proyecto.Final.model.Commerce;
import spring.edu.Proyecto.Final.repository.ICommerceRepository;
import spring.edu.Proyecto.Final.service.interfaces.ICommerceService;

import java.util.List;

@Service
public class CommerceServiceImpl implements ICommerceService {

    @Autowired
    ICommerceRepository commerceRepository;

    @Transactional
    public void create(Commerce commerceDTO) {

        Commerce commerce = new Commerce();
        commerce.setName(commerceDTO.getName());
        commerce.setLocation(commerceDTO.getLocation());
        commerce.setTelephone(commerceDTO.getTelephone());

        commerceRepository.save(commerce);
    }

    @Transactional
    public void update(Commerce commerceDTO) {
        Commerce commerce = commerceRepository.findById(commerceDTO.getId()).get();


        commerceRepository.save(commerce);
    }

    @Transactional(readOnly = true)
    public List<Commerce> getAll() {
        return commerceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Commerce getById(Integer id) {
        return commerceRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(Integer id) {
        commerceRepository.deleteById(id);
    }
}


