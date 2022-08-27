package spring.edu.Proyecto.Final.model;

import java.util.List;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Where(clause = "product_deleted=false")
@SQLDelete(sql = "UPDATE product SET product_deleted = true WHERE product_id = ?")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product",indexes = {@Index(name = "idx_product_name", columnList = "product_name")})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_details")
	private String details;
	@Column(name = "product_image")
	private String image;
	@Column(name = "product_price")
	private Double price;
	@Column(name = "product_quantity")
	private Double quantity;
	
	@ManyToOne
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category", referencedColumnName = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commerce", referencedColumnName = "commerce_id", nullable = false)
    private Commerce commerce;

	@Column(name = "product_deleted", nullable = false)
	private boolean deleted = Boolean.FALSE;




}
