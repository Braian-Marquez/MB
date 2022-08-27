package spring.edu.Proyecto.Final.service.interfaces;

import java.util.List;
import java.util.Optional;


import spring.edu.Proyecto.Final.model.*;

public interface IBudgetService {
	void create(Budget budgetDTO);
	List<Budget> getAll();
	String generarNumeroOrden();
	void delete(Budget budgetDTO);
	void save(Budget budgetDTO);
	void deleteById(Integer id);
	Optional<Budget> findById(Integer id);
	List<Budget> findByCustomer(Customer customer);


}
