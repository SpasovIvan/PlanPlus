package com.spasov.planplus.exception.UserException;

public class UserNotFoundByEmailException extends RuntimeException {
    public UserNotFoundByEmailException(String email) {
        super("User not found by email: '" + email);
    }
}
