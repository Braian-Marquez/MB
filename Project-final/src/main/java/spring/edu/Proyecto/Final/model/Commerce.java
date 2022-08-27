package spring.edu.Proyecto.Final.model;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Table(name = "commerce")
@Data
@Entity
public class Commerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commerce_id", nullable = false)
    private Integer id;

    @Column(name = "commerce_name", nullable = false)
    private String name;

    private String location;

    private String telephone;

    @OneToMany(mappedBy = "commerce", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;


    
}
