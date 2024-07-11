package com.spasov.planplus.exception.RoleException;

import com.spasov.planplus.entity.RoleEnum;

public class RoleNotFoundByNameException extends RuntimeException {
    public RoleNotFoundByNameException(RoleEnum roleEnum) {
        super("Role not found by name: " + roleEnum.name());
    }
}
