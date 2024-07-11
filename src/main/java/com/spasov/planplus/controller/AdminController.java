package com.spasov.planplus.controller;

import com.spasov.planplus.entity.*;
import com.spasov.planplus.service.OrderService;
import com.spasov.planplus.service.RoleService;
import com.spasov.planplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final OrderService orderService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, OrderService orderService) {
        this.userService = userService;
        this.roleService = roleService;
        this.orderService = orderService;
    }

    @GetMapping
    public String home(Model model) {
        List<Order> orders = orderService.findAllByOrderByTimestampAsc();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    @GetMapping("/people")
    public String staff(Model model) {
        Role roleUser = roleService.findRoleByName(RoleEnum.USER);
        List<User> users = userService.findUsersByRole(roleUser);
        Role roleAdmin = roleService.findRoleByName(RoleEnum.ADMIN);
        List<User> admins = userService.findUsersByRole(roleAdmin);
        model.addAttribute("users", users);
        model.addAttribute("admins", admins);
        return "admin/people";
    }


    @PostMapping("/changeStatus")
    public String changeOrderStatus(@RequestParam("orderId") Long orderId, @RequestParam("newStatus") String newStatus) {
        Order order = orderService.findOrderById(orderId);
        order.setStatus(OrderStatus.valueOf(newStatus));
        orderService.update(order);
        return "redirect:/admin";
    }


    // Method to handle deleting an order
    @PostMapping("/deleteOrder")
    public String deleteOrder(@RequestParam Long orderId) {
        orderService.delete(orderId);
        return "redirect:/admin";
    }
}
