package com.spasov.planplus.exception.RoleException;

import com.spasov.planplus.entity.Role;

public class RoleCanNotBeDeletedException extends RuntimeException {
    public RoleCanNotBeDeletedException(Long id) {
        super("Role can't be deleted with ID: " + id);
    }
}
