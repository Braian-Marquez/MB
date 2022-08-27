package spring.edu.Proyecto.Final.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.edu.Proyecto.Final.model.Role;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {


    private String email;

    private String password;

    private String photo;

    private Long dni;

    private String name;

    private String surname;

    private String telephone;

    private String location;

    private LocalDate dateOfBirth;

    private Role role;
}
