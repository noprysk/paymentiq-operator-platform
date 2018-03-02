package com.paymentiq.operator.platform.services;

import com.paymentiq.operator.platform.dto.VerifyUserDTO;
import com.paymentiq.operator.platform.dto.VerifyUserInfo;

public interface UserService {

    VerifyUserDTO verifyUser(VerifyUserInfo verifyUserInfo);
}
