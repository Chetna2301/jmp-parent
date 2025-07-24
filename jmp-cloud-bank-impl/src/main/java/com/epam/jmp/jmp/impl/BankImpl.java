package com.epam.jmp.jmp.impl;

import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.CreditBankCard;
import com.epam.dto.DebitBankCard;
import com.epam.dto.User;
import com.epam.jmp.api.Bank;

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
