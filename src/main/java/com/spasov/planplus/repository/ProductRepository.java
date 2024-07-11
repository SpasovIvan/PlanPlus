package com.spasov.planplus.repository;

import com.spasov.planplus.entity.Product;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
