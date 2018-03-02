package com.paymentiq.operator.platform.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

    private String userId;
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
}
