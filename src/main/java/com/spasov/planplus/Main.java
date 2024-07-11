package com.spasov.planplus;

import com.spasov.planplus.entity.Role;
import com.spasov.planplus.entity.RoleEnum;
import com.spasov.planplus.entity.User;
import com.spasov.planplus.repository.RoleRepository;
import com.spasov.planplus.service.RoleService;
import com.spasov.planplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
    }
}