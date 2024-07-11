package com.spasov.planplus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany/*(cascade = {CascadeType.PERSIST, CascadeType.MERGE})*/
    @JoinTable(name = "orders_products",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @Column(name = "phone_number", length = 12)
    @NotBlank(message = "Обязательное поле")
    @Size(min = 11, max = 12, message = "Проверьте номер телефона и попробуйте повторно")
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Column(nullable = false)
    @DateTimeFormat
    private LocalDateTime timestamp;

    @Transient
    private Duration timeframe;

    public Duration getTimeframe () {
        return Duration.between(timestamp, LocalDateTime.now());
    }

    @Builder
    public Order(User user, List<Product> products, String phoneNumber, OrderStatus status, LocalDateTime timestamp) {
        this.user = user;
        this.products = products;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.timestamp = timestamp;
    }
}
