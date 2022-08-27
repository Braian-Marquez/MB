package spring.edu.Proyecto.Final.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import spring.edu.Proyecto.Final.dto.UserDTO;
import spring.edu.Proyecto.Final.model.Role;
import spring.edu.Proyecto.Final.model.User;
import spring.edu.Proyecto.Final.service.UploadFileService;
import spring.edu.Proyecto.Final.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal) {
        ModelAndView mav = new ModelAndView("login");

        if (error != null) mav.addObject("error", "Correo electrónico o contraseña no válidos");
        if (logout != null) mav.addObject("logout", "Has salido con éxito, vuelva pronto.");
        if (principal != null) mav.setViewName("redirect:/");

        return mav;
    }

    @GetMapping("/sign-up")
    public ModelAndView signup(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("registro");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        User user = new User();
        if (principal != null) mav.setViewName("redirect:/");
        if (inputFlashMap != null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("userDTO", inputFlashMap.get("userDTO"));
        } else {
            UserDTO userDTO = new UserDTO();
            user.setRole(Role.ADMIN);
            mav.addObject("userDTO", userDTO);
        }
        return mav;
    }

    @PostMapping("/register")
    public RedirectView signup(UserDTO dto, HttpServletRequest request, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/");

        try {
            userService.createDTO(dto);
            request.login(dto.getEmail(), dto.getPassword());
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("userDTO", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/auth/sign-up");
        } catch (ServletException e) {
            attributes.addFlashAttribute("error", "Auto login fallo");
        }
        return redirect;
    }


}
