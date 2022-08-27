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
import spring.edu.Proyecto.Final.model.Commerce;
import spring.edu.Proyecto.Final.service.CommerceServiceImpl;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/shops")
@RequiredArgsConstructor
public class CommerceController {

    private final CommerceServiceImpl commerceService;


    @GetMapping
    public ModelAndView getCommerce(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("table-commerce");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("success", inputFlashMap.get("success"));

        mav.addObject("commerce", commerceService.getAll());
        return mav;
    }

    @GetMapping("/form")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("shop-form");
        mav.addObject("commerce", new Commerce());
        mav.addObject("action", "create");
        return mav;
    }

    @GetMapping("/form-commerce/{id}")
    public ModelAndView getForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("form-commerce");
        mav.addObject("commerce", commerceService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(Commerce commerce, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/shops/form");
        commerceService.create(commerce);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/update")
    public RedirectView update(Commerce commerce, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/commerce");
        commerceService.update(commerce);
        attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        return redirect;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id) {
        RedirectView redirect = new RedirectView("/commerce");
        commerceService.deleteById(id);
        return redirect;
    }
}
