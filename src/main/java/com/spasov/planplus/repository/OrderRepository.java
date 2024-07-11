package com.spasov.planplus.repository;

import com.spasov.planplus.entity.Order;
import com.spasov.planplus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByTimestampAsc();
    List<Order> findAllByUserOrderByTimestampDesc(User user);
}
