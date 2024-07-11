package com.spasov.planplus.controller;

import com.spasov.planplus.entity.Order;
import com.spasov.planplus.entity.OrderStatus;
import com.spasov.planplus.entity.Product;
import com.spasov.planplus.entity.User;
import com.spasov.planplus.service.OrderService;
import com.spasov.planplus.service.ProductService;
import com.spasov.planplus.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AnonymousController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public AnonymousController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Product> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "catalog";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<?> addToCart(@RequestParam("productId") Long productId, HttpSession session) {
        Product product = productService.findProductById(productId);

        Order cart = (Order) session.getAttribute("cart");
        if (cart == null) {
            List<Product> products = new ArrayList<>();
            products.add(product);
            cart = Order.builder()
                    .products(products)
                    .build();
        } else {
            List<Product> products = cart.getProducts();
            if (products.size() >= 10) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("В корзине может быть не более 10 услуг");
            }
            products.add(product);
            cart.setProducts(products);
        }
        session.setAttribute("cart", cart);

        return ResponseEntity.ok().body(new CartResponse(cart.getProducts().size()));
    }


    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Order cart = (Order) session.getAttribute("cart");
        if (cart == null) {
            cart = new Order(null, new ArrayList<>(), null, OrderStatus.NOT_PROCESSED, LocalDateTime.now());
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(@RequestParam("phoneNumber") String phoneNumber, HttpSession session, Principal principal) {
        Order cart = (Order) session.getAttribute("cart");
        if (cart != null) {
            cart.setPhoneNumber(phoneNumber);
            cart.setStatus(OrderStatus.NOT_PROCESSED);
            cart.setTimestamp(LocalDateTime.now());
            if (principal != null) {
                String username = principal.getName();
                userService.findByEmail(username).ifPresent(cart::setUser);
            }
            orderService.save(cart);
            session.removeAttribute("cart");
        }
        return "redirect:/";
    }

    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    static class CartResponse {
        private final int cartSize;

        public CartResponse(int cartSize) {
            this.cartSize = cartSize;
        }

        public int getCartSize() {
            return cartSize;
        }
    }
}


