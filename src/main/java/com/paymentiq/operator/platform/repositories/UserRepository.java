package com.paymentiq.operator.platform.repositories;

import com.paymentiq.operator.platform.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(User.builder().userId("demoUser").userCat("VIP_SE").kycStatus("Approved").sex("Male")
                .firstName("petro").lastName("petrov").street("main street").city("lviv").zip("80234").country("UKR")
                .email("ppetrov@test.com").dob("1985-09-24").mobile("+380971111111").balance(100.0).balanceCy("USD")
                .locale("uk_UA").build());
    }

    public Optional<User> getUserById(String userId){

        return users.stream()
                .filter(u -> u.getUserId().equals(userId) )
                .findFirst();
    }

    public void updateUserBalance(String userId, double amount) {
        users.forEach(u -> {
            if (u.getUserId().equals(userId)) {
                u.setBalance(u.getBalance() + amount);
            }
        });
    }
}
