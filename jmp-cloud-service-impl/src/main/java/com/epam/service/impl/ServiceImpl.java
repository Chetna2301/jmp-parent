package com.epam.service.impl;

import com.epam.dto.dto.BankCard;
import com.epam.dto.dto.Subscription;
import com.epam.dto.dto.User;
import com.epam.service.exceptions.SubscriptionNotFoundException;
import com.epam.api.service.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public class ServiceImpl implements Service {
    @Override
    public void subscribe(BankCard bankCard) {

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return Optional.of(
                getAllSubscriptions().stream()
                        .filter(subscription -> subscription.getBankcard().equals(cardNumber))
                        .findFirst()
                        .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found for card: " + cardNumber)));
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return Collections.EMPTY_LIST;
    }


    @Override
    public boolean isPlayableUser(User user) {
        return Period.between(user.getBirthday(), LocalDate.now()).getYears() > 18;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> subscriptionCondition) {
        return getAllSubscriptions().stream().filter(subscriptionCondition).toList();
    }
}
