package com.spasov.planplus.exception.OrderException;

public class OrderCanNotBeDeletedException extends RuntimeException {
    public OrderCanNotBeDeletedException(Long id) {
        super("Order can't be deleted with ID: " + id);
    }
}
