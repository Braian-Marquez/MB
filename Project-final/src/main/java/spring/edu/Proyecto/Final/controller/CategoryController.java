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
import spring.edu.Proyecto.Final.model.Category;
import spring.edu.Proyecto.Final.service.CategoryServiceImpl;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ModelAndView getCategory(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("categories");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("categories", categoryService.getAll());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("category-form");
        mav.addObject("category", new Category());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("category-form");
        mav.addObject("category", categoryService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Category category, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/categories/form");
        categoryService.create(category);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Category category, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/categories");
        categoryService.update(category);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/categories");
        categoryService.deleteById(id);
        return redirect;
    }

    @PostMapping("/search/{name}")
    public RedirectView findByCategoryName(@PathVariable String name){
        RedirectView redirect = new RedirectView("/products/");
        categoryService.findByName(name);
        return redirect;
    }
}
