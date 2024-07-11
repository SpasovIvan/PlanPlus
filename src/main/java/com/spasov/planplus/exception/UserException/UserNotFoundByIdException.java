package com.spasov.planplus.exception.UserException;

public class UserNotFoundByIdException extends RuntimeException {
    public UserNotFoundByIdException(Long id) {
        super("User not found by ID: '" + id);
    }
}
