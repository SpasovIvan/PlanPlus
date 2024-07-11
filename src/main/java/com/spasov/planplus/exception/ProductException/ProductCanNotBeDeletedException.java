package com.spasov.planplus.exception.ProductException;

public class ProductCanNotBeDeletedException extends RuntimeException {
    public ProductCanNotBeDeletedException(Long id) {
        super("Product can't be deleted with ID: " + id);
    }
}
