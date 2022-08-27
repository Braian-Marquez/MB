
package spring.edu.Proyecto.Final.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.model.Product;
import spring.edu.Proyecto.Final.model.User;
import spring.edu.Proyecto.Final.service.*;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CommerceServiceImpl commerceService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private UploadFileService upload;

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/show";
    }


    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("products/product-form");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("product", inputFlashMap.get("product"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("product", new Product());
        }

        mav.addObject("category", categoryService.getAll());
        mav.addObject("commerce", commerceService.getAll());
        mav.addObject("action", "create");
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView create(Product product, @RequestParam("img") MultipartFile photo, HttpSession session) throws IOException {

        ModelAndView mav = new ModelAndView("product-form");

        Customer customer = customerService.getById(Integer.parseInt(session.getAttribute("id").toString()));
        product.setCustomer(customer);

        //imagen
        if (product.getId() == null) { // cuando se crea un product
            String nombreImage = upload.save(photo);
            product.setImage(nombreImage);
        } else {

        }

        productService.save(product);
        return mav;
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = new Product();
        //	Optional<Product> optionalProduct=productService.get(id);
        //	product= optionalProduct.get();

        model.addAttribute("product", product);

        return "products/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        Product p = new Product();
        // p=productService.get(product.getId()).get();

        if (file.isEmpty()) { // editamos el product pero no cambiamos la imagem

            product.setImage(p.getImage());
        } else {// cuando se edita tbn la imagen
            //eliminar cuando no sea la imagen por defecto
            if (!p.getImage().equals("default.jpg")) {
                upload.deleteImage(p.getImage());
            }
            String nameImage = upload.save(file);
            product.setImage(nameImage);
        }
        product.setCustomer(p.getCustomer());
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Product p = new Product();
        p = productService.getById(id);


        //eliminar cuando no sea la imagen por defecto

        if (p.getImage() == null) {
            p.setImage("default.jpg");
        }

        productService.delete(id);
        return "redirect:/products";
    }


}

