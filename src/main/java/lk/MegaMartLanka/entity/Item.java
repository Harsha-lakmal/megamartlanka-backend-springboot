package lk.MegaMartLanka.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    private  String imgPath ;

    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

    @JsonIgnore
    @OneToOne(mappedBy = "item", cascade =  CascadeType.PERSIST)
    private Stock stock;

}
