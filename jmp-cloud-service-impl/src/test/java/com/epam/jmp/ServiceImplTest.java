package com.epam.jmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.dto.BankCard;
import com.epam.dto.Subscription;
import com.epam.dto.User;
import com.epam.jmp.service.exceptions.SubscriptionNotFoundException;
import com.epam.jmp.service.impl.ServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class ServiceImplTest {

    @Test
    void getSubscriptionByBankCardNumberShouldReturnSubscriptionWhenExists() {
        var cardNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        var bankCard =
                new BankCard(
                        cardNumber,
                        new User("John", "Connor", LocalDate.of(2000, 1, 1)));
        var expected = new Subscription(
                bankCard.getNumber(), LocalDate.now().plusDays(10));

        var service = new ServiceImpl() {
            @Override
            public List<Subscription> getAllSubscriptions() {
                return List.of(expected);
            }
        };

        Optional<Subscription> result = service.getSubscriptionByBankCardNumber(cardNumber);
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void getSubscriptionByBankCardNumberShouldThrowWhenNotFound() {
        var service = new ServiceImpl() {
            @Override
            public List<Subscription> getAllSubscriptions() {
                return List.of();
            }
        };

        assertThrows(SubscriptionNotFoundException.class, () ->
                service.getSubscriptionByBankCardNumber("notfound"));
    }

    @Test
    void isPlayableUserShouldReturnTrueForAdult() {
        var adult = new User("Alice", "Johnson", LocalDate.of(1990, 1, 1));
        var service = new ServiceImpl();
        assertTrue(service.isPlayableUser(adult));
    }

    @Test
    void isPlayableUserShouldReturnFalseForMinor() {
        var minor = new User("Bob", "Marley", LocalDate.now().minusYears(17));
        var service = new ServiceImpl();
        assertFalse(service.isPlayableUser(minor));
    }

    @Test
    void getAllSubscriptionsByConditionShouldFilterCorrectly() {
        var bankCard1 = new BankCard("111", new User("User1", "User1", LocalDate.of(2000, 1, 1)));
        var sub1 = new Subscription(
                bankCard1.getNumber(), LocalDate.now().plusDays(10));
        var bankCard2 = new BankCard("222", new User("User2", "User1", LocalDate.of(2000, 1, 1)));
        var sub2 = new Subscription(
                bankCard2.getNumber(), LocalDate.now().plusDays(10));

        var service = new ServiceImpl() {
            @Override
            public List<Subscription> getAllSubscriptions() {
                return List.of(sub1, sub2);
            }
        };

        var allSubscriptions = service.getAllSubscriptionsByCondition(s -> s.getBankcard().equals("111"));
        assertEquals(1, allSubscriptions.size());
        assertEquals(sub1, allSubscriptions.get(0));
    }
}
