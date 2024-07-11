package com.spasov.planplus.service;

import com.spasov.planplus.entity.Product;
import com.spasov.planplus.entity.Role;
import com.spasov.planplus.exception.ProductException.ProductCanNotBeDeletedException;
import com.spasov.planplus.exception.ProductException.ProductCanNotBeUpdatedException;
import com.spasov.planplus.exception.ProductException.ProductNotFoundByIdException;
import com.spasov.planplus.exception.RoleException.RoleCanNotBeDeletedException;
import com.spasov.planplus.exception.RoleException.RoleCanNotBeUpdatedException;
import com.spasov.planplus.exception.RoleException.RoleNotFoundByIdException;
import com.spasov.planplus.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        log.info("Saving product with id: {}", product.getId());
        return productRepository.save(product);
    }

    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            log.info(("Deleting product with id: {}"), id);
            productRepository.deleteById(id);
        } else {
            log.error(("Product can't be deleted with id: {}"), id);
            throw new ProductCanNotBeDeletedException(id);
        }
    }

    public Product update(Product product) {
        if (productRepository.existsById(product.getId())) {
            log.info(("Updating product: {}"), product.getId());
            return productRepository.save(product);
        } else {
            log.error(("Product can't be updated with id: {}"), product.getId());
            throw new ProductCanNotBeUpdatedException(product);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProduct() {
        log.info("Finding all products");
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        log.info("Finding product by id: {}", id);
        return productRepository.findById(id).orElseThrow(() -> {
            log.error("Product not found by id: {}", id);
            return new ProductNotFoundByIdException(id);
        });
    }
}
