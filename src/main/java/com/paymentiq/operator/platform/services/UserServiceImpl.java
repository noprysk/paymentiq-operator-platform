package com.paymentiq.operator.platform.services;

import com.paymentiq.operator.platform.dto.VerifyUserDTO;
import com.paymentiq.operator.platform.dto.VerifyUserInfo;
import com.paymentiq.operator.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public VerifyUserDTO verifyUser(VerifyUserInfo verifyUserInfo) {
        // TODO: this is not any sessionId validation except it is not empty

        return checkUser(userRepository, verifyUserInfo.getUserId());
    }
}
