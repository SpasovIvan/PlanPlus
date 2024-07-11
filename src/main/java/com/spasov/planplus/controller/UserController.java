package com.spasov.planplus.controller;

import com.spasov.planplus.entity.Order;
import com.spasov.planplus.entity.User;
import com.spasov.planplus.service.OrderService;
import com.spasov.planplus.service.ProductService;
import com.spasov.planplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/my-orders")
    public String viewUserOrders(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Optional<User> userOptional = userService.findByEmail(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                List<Order> orders = orderService.findAllByUserOrderByTimestampDesc(user);
                model.addAttribute("orders", orders);
            }
        }
        return "user/my-orders";
    }

}
