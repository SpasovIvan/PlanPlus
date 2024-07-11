package com.spasov.planplus.service;

import com.spasov.planplus.entity.Role;
import com.spasov.planplus.entity.User;
import com.spasov.planplus.exception.UserException.UserCanNotBeDeletedException;
import com.spasov.planplus.exception.UserException.UserCanNotBeUpdatedException;
import com.spasov.planplus.exception.UserException.UserNotFoundByEmailException;
import com.spasov.planplus.exception.UserException.UserNotFoundByIdException;
import com.spasov.planplus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            log.error(("User not found by email: {}"), email);
            return new UsernameNotFoundException("Not found user by '" + email + "' email");
        });
    }

    public User save(User user) {
        log.info(("Saving user with id: {}"), user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            log.info(("Deleting user with id: {}"), id);
            userRepository.deleteById(id);
        } else {
            log.error(("User can't be deleted with id: {}"), id);
            throw new UserCanNotBeDeletedException(id);
        }
    }

    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            log.info(("Updating user: {}"), user.getId());
            return userRepository.save(user);
        } else {
            log.error(("User can't be updated with id: {}"), user.getId());
            throw new UserCanNotBeUpdatedException(user);
        }
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        log.info("Finding all users");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        log.info(("Finding user by id: {}"), id);
        return userRepository.findById(id).orElseThrow(() -> {
            log.error(("User not found by id: {}"), id);
            return new UserNotFoundByIdException(id);
        });
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByRole(Role role) {
        log.info("Find user by role containing: {}", role.getName());
        return userRepository.findAllByRolesContaining(role);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
