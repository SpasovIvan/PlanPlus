package com.spasov.planplus.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String name;

    @Lob
    private String description;

    private int price;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public Product(String name, String description, int price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
