package spring.edu.Proyecto.Final.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import spring.edu.Proyecto.Final.model.Budget;
import spring.edu.Proyecto.Final.service.BudgetServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetServiceImpl budgetService;

    @GetMapping
    public ModelAndView getBudget(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("budget");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("budget", budgetService.getAll());
        return mav;
    }
    @GetMapping("/my-list")
    public ModelAndView getMyList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("my-list");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("budget", budgetService.getAll());
        return mav;
    }

    @GetMapping("/form-budget")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("form-budget");
        mav.addObject("budget", new Budget());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-budget/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-budget");
        mav.addObject("budget", budgetService.findById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Budget budget, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/budget");
        budgetService.create(budget);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Budget budget, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/budget");
        budgetService.save(budget);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/budget");
        budgetService.deleteById(id);
        return redirect;
    }
}

