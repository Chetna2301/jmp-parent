package com.epam.jmp.api.service;

import com.epam.dto.BankCard;
import com.epam.dto.Subscription;
import com.epam.dto.User;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptions();

    default double getAverageUserAge() {
        return getAllUsers().stream().mapToDouble(user -> Duration.between(user.getBirthday(),
                LocalDate.now()).get(ChronoUnit.YEARS)).average().orElse(0);
    }

    boolean isPlayableUser(User user);

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> subscriptionCondition);
}
