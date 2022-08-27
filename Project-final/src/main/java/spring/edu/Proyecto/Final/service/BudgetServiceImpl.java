package spring.edu.Proyecto.Final.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.edu.Proyecto.Final.model.Budget;
import spring.edu.Proyecto.Final.model.BudgetDetails;
import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.model.Product;
import spring.edu.Proyecto.Final.repository.IBudgetRepository;
import spring.edu.Proyecto.Final.service.interfaces.IBudgetService;


@Service
public class BudgetServiceImpl implements IBudgetService {

    @Autowired
    private IBudgetRepository budgetRepository;


    @Override
    public void create(Budget budgetDTO) {

        Budget budget = new Budget();
        budget.setName(budgetDTO.getName());
        budget.setCreationDate(budgetDTO.getCreationDate());
        budget.setReceivedDate(budgetDTO.getReceivedDate());


        budgetRepository.save(budget);

    }
    @Override
    @Transactional
    public void save(Budget budgetDTO) {
        budgetRepository.save(budgetDTO);
    }


    @Transactional(readOnly = true)
    public List<Budget> getAll() {

        return budgetRepository.findAll();
    }

    @Override
    public String generarNumeroOrden() {

            int numero=0;
            String numeroConcatenado="";

            List<Budget> budget = getAll();

            List<Integer> numeros= new ArrayList<Integer>();

            budget.stream().forEach(o -> numeros.add( Integer.parseInt( o.getNum())));

            if (budget.isEmpty()) {
                numero=1;
            }else {
                numero= numeros.stream().max(Integer::compare).get();
                numero++;
            }

            if (numero<10) { //0000001000
                numeroConcatenado="000000000"+String.valueOf(numero);
            }else if(numero<100) {
                numeroConcatenado="00000000"+String.valueOf(numero);
            }else if(numero<1000) {
                numeroConcatenado="0000000"+String.valueOf(numero);
            }else if(numero<10000) {
                numeroConcatenado="0000000"+String.valueOf(numero);
            }

            return numeroConcatenado;
        }


    @Override
    @Transactional
    public void delete(Budget categoryDTO) {
        budgetRepository.delete(categoryDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> findById(Integer id) {
        return budgetRepository.findById(id);
    }


    @Transactional
    public void deleteById(Integer id) {
        budgetRepository.deleteById(id);
    }

    @Override
    public List<Budget> findByCustomer(Customer customer) {
        return budgetRepository.findByCustomer(customer);
    }


}

