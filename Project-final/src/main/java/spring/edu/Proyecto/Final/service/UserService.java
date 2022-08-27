package spring.edu.Proyecto.Final.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spring.edu.Proyecto.Final.dto.UserDTO;
import spring.edu.Proyecto.Final.model.Customer;
import spring.edu.Proyecto.Final.model.Role;
import spring.edu.Proyecto.Final.model.User;
import spring.edu.Proyecto.Final.repository.ICustomerRepository;
import spring.edu.Proyecto.Final.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;


    @Transactional
    public void update(User dto) {
        User user = userRepository.findById(dto.getId()).get();

        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        userRepository.save(user);
    }
    @Transactional
    public void createDTO(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("Ya existe un usuario asociado al correo ingresado");

        User user = new User();
        Customer customer = new Customer();

        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setUser(user);
        if (userRepository.findAll().isEmpty()) user.setRole(Role.ADMIN);
        else user.setRole((Role.CUSTOMER));

        customerRepository.save(customer);
        userRepository.save(user);
       // emailService.send(dto.getEmail(),dto.getName());
    }
    @Transactional(readOnly = true)
    public User getById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("There is no user associated with the email entered"));
        GrantedAuthority authority = () -> "ROLE_" + user.getRole().name();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("id", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole().name());
        session.setAttribute("name", user.getCustomer().getName());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), singletonList(authority));
    }
}
