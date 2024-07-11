package com.spasov.planplus.exception.RoleException;

import com.spasov.planplus.entity.Role;

public class RoleCanNotBeUpdatedException extends RuntimeException {
    public RoleCanNotBeUpdatedException(Role role) {
        super("Role with ID: '" + role.getId() + "' can't be updated");
    }
}
