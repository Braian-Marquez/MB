package spring.edu.Proyecto.Final.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import spring.edu.Proyecto.Final.model.Budget;
import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.model.User;
import spring.edu.Proyecto.Final.repository.UserRepository;
import spring.edu.Proyecto.Final.service.CustomerServiceImpl;
import spring.edu.Proyecto.Final.service.UploadFileService;
import spring.edu.Proyecto.Final.service.UserService;
import spring.edu.Proyecto.Final.service.interfaces.IBudgetService;

// import spring.edu.Proyecto.Final.service.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private IBudgetService budgetService;
    @Autowired
    private CustomerServiceImpl customerService;
    private final UploadFileService upload;
    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("/budget/{id}")
    public String getBudgetDetails(@PathVariable Integer id, HttpSession session, Model model) {

        Optional<Budget> budget = budgetService.findById(id);

        model.addAttribute("details", budget.get().getDetails());


        //session
        model.addAttribute("session", session.getAttribute("id"));
        return "usuario/budgetdetails";
    }

    @GetMapping("/budgets")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("session", session.getAttribute("id"));

        Customer customer= customerService.findById(  Integer.parseInt(session.getAttribute("id").toString()) );
        List<Budget> budget= budgetService.findByCustomer(customer);


        model.addAttribute("budgets", budget);

        return "usuario/budgets";
    }


    @GetMapping("/profile/{id}")
    public ModelAndView getProfile(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("user-profile");
        mav.addObject("customer", customerService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/profile")
    public RedirectView update(Customer customer, @RequestParam("image") MultipartFile photo) {
        RedirectView mav = new RedirectView("/");


        customerService.update(customer, photo);
        return mav;
    }
}