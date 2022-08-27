package spring.edu.Proyecto.Final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.edu.Proyecto.Final.model.BudgetDetails;
import spring.edu.Proyecto.Final.model.Product;
import spring.edu.Proyecto.Final.repository.IBudgetDetailsRepository;
import spring.edu.Proyecto.Final.service.interfaces.IBudgetDetailsService;


@Service
public class BudgetDetailsServiceImpl implements IBudgetDetailsService {
	
	@Autowired
	private IBudgetDetailsRepository budgetDetailsRepository;

	@Override
	public BudgetDetails save(BudgetDetails budgetDetails) {
		return budgetDetailsRepository.save(budgetDetails);
	}



	@Transactional(readOnly = true)
    public List<BudgetDetails> getAll() {

        return budgetDetailsRepository.findAll();
    }

	@Transactional
	public void deleteById(Integer id) {
		budgetDetailsRepository.deleteById(id);
	}
}