package com.spasov.planplus.exception.RoleException;

public class RoleNotFoundByIdException extends RuntimeException {
    public RoleNotFoundByIdException (Long id) {
        super("Role not found by ID: " + id);
    }
}
