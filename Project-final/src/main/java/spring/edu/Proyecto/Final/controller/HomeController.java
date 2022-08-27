package spring.edu.Proyecto.Final.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import spring.edu.Proyecto.Final.model.*;
import spring.edu.Proyecto.Final.service.*;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    private final ProductServiceImpl productService;
    private final BudgetServiceImpl budgetService;
    private final BudgetDetailsServiceImpl budgetDetailsService;
    private final CustomerServiceImpl customerService;
    // to store order details
    private List<BudgetDetails> details = new ArrayList<BudgetDetails>();

    private final UserService userService;
    // budget
    private Budget budget = new Budget();

    @GetMapping("")
    public ModelAndView getAllProducts(HttpSession session) {
        ModelAndView mav = new ModelAndView("usuario/home");
        mav.addObject("products", productService.findAll());
        //session
        mav.addObject("session", session.getAttribute("id_user"));

        return mav;
    }


    @GetMapping("/{id}")
    public ModelAndView productId(@PathVariable Integer id) {

        ModelAndView mav = new ModelAndView("usuario/product");

        mav.addObject("product", productService.getById(id));

        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addProduct(@RequestParam Integer id, @RequestParam Integer quantity) {

        ModelAndView mav = new ModelAndView("usuario/item-list");
        Double totalSum = 0.0;
        Product product = productService.getById(id);
        BudgetDetails budgetDetails = new BudgetDetails();
        budgetDetails.setQuantity(quantity);
        budgetDetails.setProduct(product);
        budgetDetails.setPrice(product.getPrice());
        budgetDetails.setName(product.getName());
        budgetDetails.setTotal(product.getPrice() * quantity);

        details.add(budgetDetails);
        Integer idProducto = product.getId();
        boolean ingresado = details.stream().anyMatch(p -> p.getProduct().getId() == idProducto);

        if (!ingresado) {
            details.add(budgetDetails);
        }
        totalSum = details.stream().mapToDouble(dt -> dt.getTotal()).sum();
        budget.setTotal(totalSum);

        mav.addObject("budgetDetails", details);
        mav.addObject("budget", budget);

        return mav;
    }

    @GetMapping("/budget_details")
    public ModelAndView budgetResume(HttpSession session) {
        ModelAndView mav = new ModelAndView("usuario/resume");
        Integer id = (Integer) session.getAttribute("id");


        User user = userService.getById(id);

        mav.addObject("budgetDetails", details);
        mav.addObject("budget", budget);
        mav.addObject("customer", user.getCustomer());


        return mav;
    }


    @PostMapping("/delete/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model) {

        // new product list
        List<BudgetDetails> newBudget = new ArrayList<BudgetDetails>();

        for (BudgetDetails budgetDetails : details) {
            if (budgetDetails.getProduct().getId() != id) {
                newBudget.add(budgetDetails);
            }
        }

        // new list with the remaining products
        details = newBudget;

        Double totalSum = 0.0;
        totalSum = details.stream().mapToDouble(dt -> dt.getTotal()).sum();

        budget.setTotal(totalSum);
        model.addAttribute("budgetDetails", details);
        model.addAttribute("budget", budget);

        return "usuario/item-list";
    }

    @GetMapping("/budgets")
    public String budget(Model model, HttpSession session) {

        Integer id = (Integer) session.getAttribute("id");


        User user = userService.getById(id);

        model.addAttribute("details", details);
        model.addAttribute("budget", budget);
        model.addAttribute("customer", user.getCustomer());

        return "usuario/budgets";
    }

    // save budget
    @GetMapping("/saveBudget")
    public String saveBudget(HttpSession session) {


        LocalDate fechaCreacion = LocalDate.now();
        budget.setCreationDate(fechaCreacion);
        budget.setNum(budgetService.generarNumeroOrden());


        //user
        Customer customer = customerService.findById(Integer.parseInt(session.getAttribute("id").toString()));
        budget.setCustomer(customer);
        budgetService.save(budget);


        //save details
        for (BudgetDetails dt : details) {
            dt.setBudget(budget);
            budgetDetailsService.save(dt);
        }

        ///clean list and budget
        budget = new Budget();
        details.clear();

        return "redirect:/customer/budgets";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String name, Model model) {

        List<Product> products = productService.findAll()
                .stream().filter(p -> p.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());


        if(!products.isEmpty()){
            model.addAttribute("products", products);
        } else {
            model.addAttribute("message", "No se ha encontrado el producto buscado, por favor ingrese otro nombre");
        }


        return "usuario/home";
    }
}