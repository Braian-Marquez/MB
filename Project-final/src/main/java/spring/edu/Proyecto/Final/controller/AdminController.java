package spring.edu.Proyecto.Final.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.edu.Proyecto.Final.model.Budget;
import spring.edu.Proyecto.Final.model.Product;
import spring.edu.Proyecto.Final.service.BudgetServiceImpl;


import spring.edu.Proyecto.Final.service.ProductServiceImpl;
import spring.edu.Proyecto.Final.service.UserService;


@RequestMapping("/administrador")
@Controller
public class AdminController {

	@Autowired
	private  ProductServiceImpl productService;
	@Autowired
	private UserService userService;
	@Autowired
	private  BudgetServiceImpl budgetService;

	public AdminController(ProductServiceImpl productService, UserService userService, BudgetServiceImpl budgetService) {
		this.productService = productService;
		this.userService = userService;
		this.budgetService = budgetService;
	}


	@GetMapping("/")
	public String home(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("users", userService.getAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/budgets")
	public String ordenes(Model model) {
		model.addAttribute("budgets", budgetService.getAll());
		return "administrador/ordenes";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		
		Optional<Budget> budget= budgetService.findById(id);
		
		model.addAttribute("details", budget.get());
		return "administrador/detalleorden";
	}
	
	
}
