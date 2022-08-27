package spring.edu.Proyecto.Final.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import spring.edu.Proyecto.Final.dto.UserDTO;
import spring.edu.Proyecto.Final.model.Budget;
import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.model.User;
import spring.edu.Proyecto.Final.service.BudgetServiceImpl;
import spring.edu.Proyecto.Final.service.CustomerServiceImpl;
import spring.edu.Proyecto.Final.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private BudgetServiceImpl budgetService;

    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();



    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("session", session.getAttribute("id_customer"));

        Customer customer = customerService.findById(Integer.parseInt(session.getAttribute("id_customer").toString()));
        List<Budget> budgets = budgetService.findByCustomer(customer);
        logger.info("ordenes {}", budgets);

        model.addAttribute("budget", budgets);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        logger.info("Id de la orden: {}", id);
        //Optional<Budget> budget = budgetService.findById(id);

        //	model.addAttribute("details", budget.getDetails());


        //session
        model.addAttribute("session", session.getAttribute("id_user"));
        return "usuario/detalle_compra";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("id_user");
        return "redirect:/";

    }
}
