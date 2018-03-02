package com.paymentiq.operator.platform.services;

import com.paymentiq.operator.platform.domain.User;
import com.paymentiq.operator.platform.dto.VerifyUserDTO;
import com.paymentiq.operator.platform.exceptions.InconsistentDataApiException;
import com.paymentiq.operator.platform.exceptions.UserNotFoundApiException;
import com.paymentiq.operator.platform.repositories.UserRepository;

import java.util.Optional;

public abstract class BaseService {

    protected VerifyUserDTO checkUser(UserRepository userRepository, String userId) {
        Optional<User> user = userRepository.getUserById(userId);

        return VerifyUserDTO
                .create(user.orElseThrow(() -> new UserNotFoundApiException(userId)));
    }

    protected VerifyUserDTO checkUser(UserRepository userRepository, String userId, String currency) {
        Optional<User> user = userRepository.getUserById(userId);

        User userData =
                user.orElseThrow(() -> new UserNotFoundApiException(userId));

        if (!userData.getBalanceCy().equals(currency)) {
            throw new InconsistentDataApiException(userId);
        }

        return VerifyUserDTO.create(userData);
    }
}
