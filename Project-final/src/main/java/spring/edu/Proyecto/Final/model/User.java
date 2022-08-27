package spring.edu.Proyecto.Final.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user", indexes = {@Index(name = "idx_email", columnList = "email")})
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "email", length = 60, unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(STRING)
	@Column(name = "role", nullable = false)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "user")
	private Customer customer;

}
