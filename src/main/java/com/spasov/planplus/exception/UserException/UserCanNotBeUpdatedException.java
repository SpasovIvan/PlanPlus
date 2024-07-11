package com.spasov.planplus.exception.UserException;

import com.spasov.planplus.entity.User;

public class UserCanNotBeUpdatedException extends RuntimeException {
    public UserCanNotBeUpdatedException(User user) {
        super("User can't be updated with ID: " + user.getId());
    }
}
