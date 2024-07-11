package com.spasov.planplus.repository;

import com.spasov.planplus.entity.Role;
import com.spasov.planplus.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleEnum roleEnum);
}
