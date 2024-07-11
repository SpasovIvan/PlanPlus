package com.spasov.planplus.exception.UserException;

public class UserCanNotBeDeletedException extends RuntimeException {
    public UserCanNotBeDeletedException(Long id) {
        super("User can not be deleted with ID: " + id);
    }

}
