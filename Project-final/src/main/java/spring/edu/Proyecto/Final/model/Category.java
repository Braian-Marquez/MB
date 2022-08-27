package spring.edu.Proyecto.Final.model;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category", indexes = {@Index(name = "idx_category_name", columnList = "category_name")})
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

}

