package com.spasov.planplus.service;

import com.spasov.planplus.entity.Order;
import com.spasov.planplus.entity.Role;
import com.spasov.planplus.entity.User;
import com.spasov.planplus.exception.OrderException.OrderCanNotBeDeletedException;
import com.spasov.planplus.exception.OrderException.OrderCanNotBeUpdatedException;
import com.spasov.planplus.exception.OrderException.OrderNotFoundByIdException;
import com.spasov.planplus.exception.RoleException.RoleCanNotBeUpdatedException;
import com.spasov.planplus.exception.RoleException.RoleNotFoundByIdException;
import com.spasov.planplus.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        log.info("Saving order with id: {}", order.getId());
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order can't be deleted with id: {}", id);
                    return new OrderCanNotBeDeletedException(id);
                });
        log.info("Deleting order with id: {}", id);
        orderRepository.delete(order);
    }

    public Order update(Order order) {
        return orderRepository.findById(order.getId())
                .map(existingOrder -> {
                    log.info("Updating order: {}", order.getId());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> {
                    log.error("Order can't be updated with id: {}", order.getId());
                    return new OrderCanNotBeUpdatedException(order);
                });
    }

    /*@Transactional(readOnly = true)
    public List<Order> findAllByOrderByTimestampDesc() {
        log.info("Finding all orders by timestamp desc");
        return orderRepository.findAllByOrderByTimestampDesc();
    }*/

    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        log.info("Finding all orders");
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order findOrderById(Long id) {
        log.info("Finding role by id: {}", id);
        return orderRepository.findById(id).orElseThrow(() -> {
            log.error("Order not found by id: {}", id);
            return new OrderNotFoundByIdException(id);
        });
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByUserOrderByTimestampDesc(User user) {
        log.info("Finding all orders by user by timestamp desc");
        return orderRepository.findAllByUserOrderByTimestampDesc(user);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByOrderByTimestampAsc() {
        log.info("Finding all orders by user orderBy timestamp asc");
        return orderRepository.findAllByOrderByTimestampAsc();
    }
}
