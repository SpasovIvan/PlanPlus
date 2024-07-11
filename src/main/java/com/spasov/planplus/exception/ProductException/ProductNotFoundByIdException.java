package com.spasov.planplus.exception.ProductException;

public class ProductNotFoundByIdException extends RuntimeException {
    public ProductNotFoundByIdException(Long id) {
        super("Product not found by ID: " + id);
    }
}
