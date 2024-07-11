package com.spasov.planplus.exception.OrderException;

public class OrderNotFoundByIdException extends RuntimeException {
    public OrderNotFoundByIdException(Long id) {
        super("Order not found by ID: '" + id);
    }
}
