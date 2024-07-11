package com.spasov.planplus.service;

import com.spasov.planplus.entity.Role;
import com.spasov.planplus.entity.RoleEnum;
import com.spasov.planplus.exception.RoleException.RoleCanNotBeDeletedException;
import com.spasov.planplus.exception.RoleException.RoleCanNotBeUpdatedException;
import com.spasov.planplus.exception.RoleException.RoleNotFoundByIdException;
import com.spasov.planplus.exception.RoleException.RoleNotFoundByNameException;
import com.spasov.planplus.exception.UserException.UserCanNotBeUpdatedException;
import com.spasov.planplus.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        log.info("Saving role with id: {}", role.getId());
        return roleRepository.save(role);
    }

    public void delete(Long id) {
        if (roleRepository.existsById(id)) {
            log.info(("Deleting role with id: {}"), id);
            roleRepository.deleteById(id);
        } else {
            log.error(("Role can't be deleted with id: {}"), id);
            throw new RoleCanNotBeDeletedException(id);
        }
    }

    public Role update(Role role) {
        if (roleRepository.existsById(role.getId())) {
            log.info(("Updating role: {}"), role.getId());
            return roleRepository.save(role);
        } else {
            log.error(("Role can't be updated with id: {}"), role.getId());
            throw new RoleCanNotBeUpdatedException(role);
        }
    }

    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        log.info("Finding all roles");
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findRoleById(Long id) {
        log.info("Finding role by id: {}", id);
        return roleRepository.findById(id).orElseThrow(() -> {
            log.error("Role not found by id: {}", id);
            return new RoleNotFoundByIdException(id);
        });
    }

    public Role findRoleByName(RoleEnum roleEnum) {
        return roleRepository.findRoleByName(roleEnum).orElseThrow(() -> {
            log.error(("Role not found by name: {}"), roleEnum.name());
            return new RoleNotFoundByNameException(roleEnum);
        });

    }


}
