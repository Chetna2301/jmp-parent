package com.epam.bank.impl;

import com.epam.dto.dto.BankCard;
import com.epam.dto.dto.BankCardType;
import com.epam.dto.dto.CreditBankCard;
import com.epam.dto.dto.DebitBankCard;
import com.epam.dto.dto.User;
import com.epam.api.Bank;

import java.util.UUID;

public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        var cardNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

        return switch (cardType) {
            case CREDIT -> new CreditBankCard(cardNumber, user, 10000.0); // примерный кредитный лимит
            case DEBIT -> new DebitBankCard(cardNumber, user, 0.0);       // начальный баланс
        };
    }
}
