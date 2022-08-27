package spring.edu.Proyecto.Final.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private LocalDate creationDate;
	private Date receivedDate;
	private String num;


	private Double total;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "budget")
	private List<BudgetDetails> details;
	
	
}
