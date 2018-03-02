package com.paymentiq.operator.platform.dto;

import com.paymentiq.operator.platform.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyUserDTO {
    private String userId;
    private boolean success;
    private String userCat;
    private String kycStatus;
    private String sex;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private String country;
    private String email;
    private String dob;
    private String mobile;
    private double balance;
    private String balanceCy;
    private String locale;

    public static VerifyUserDTO create(User user) {

        return VerifyUserDTO.builder()
                .userId(user.getUserId()).success(true).userCat(user.getUserCat())
                .kycStatus(user.getKycStatus()).sex(user.getSex()).firstName(user.getFirstName())
                .lastName(user.getLastName()).street(user.getStreet()).city(user.getCity()).zip(user.getZip())
                .country(user.getCountry()).email(user.getEmail()).dob(user.getDob()).mobile(user.getMobile())
                .balance(user.getBalance()).balanceCy(user.getBalanceCy()).locale(user.getLocale())
                .build();
    }
}
