package com.spasov.planplus.exception.ProductException;

import com.spasov.planplus.entity.Product;

public class ProductCanNotBeUpdatedException extends RuntimeException {
    public ProductCanNotBeUpdatedException(Product product) {
        super("Product with ID: '" + product.getId() + "' can't be updated");
    }
}
