package lk.MegaMartLanka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @Column(nullable = false)
    private Double orderTotal;
    @ManyToMany
    @JoinTable(name = "order_Item", joinColumns = @JoinColumn(name = "order_Id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "item_Id", referencedColumnName = "id"))
    private List<Item> items;

    @PrePersist
    protected void onCreate() {
        if (this.orderDateTime == null) {
            this.orderDateTime = LocalDateTime.now ();
        }
    }


}
