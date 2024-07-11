package com.spasov.planplus.controller;

import com.spasov.planplus.entity.Role;
import com.spasov.planplus.entity.RoleEnum;
import com.spasov.planplus.entity.User;
import com.spasov.planplus.service.RoleService;
import com.spasov.planplus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder; // Добавляем PasswordEncoder

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder; // Инициализируем PasswordEncoder
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Пользователь с таким адресом электронной почты уже существует!");
            return "registration";
        }

        Role role = roleService.findRoleByName(RoleEnum.USER);
        user.setRoles(Set.of(role));
        String rawPassword = user.getPassword(); // Сохраняем незашифрованный пароль
        userService.save(user);

        // Авторизация пользователя после регистрации
        authenticateUserAndSetSession(user, rawPassword, request); // Передаем незашифрованный пароль

        redirectAttributes.addFlashAttribute("registrationSuccess", true);

        return "redirect:/user";
    }

    private void authenticateUserAndSetSession(User user, String rawPassword, HttpServletRequest request) {
        String email = user.getEmail();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, rawPassword);
        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        authToken.eraseCredentials();
    }
}
