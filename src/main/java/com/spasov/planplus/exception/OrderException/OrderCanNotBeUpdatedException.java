package com.spasov.planplus.exception.OrderException;

import com.spasov.planplus.entity.Order;

public class OrderCanNotBeUpdatedException extends RuntimeException {
    public OrderCanNotBeUpdatedException(Order order) {
        super("Order with ID: '" + order.getId() + "' can't be updated");
    }
}
