package com.epam.jmp.jmp;
import com.epam.jmp.jmp.impl.BankImpl;
import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.CreditBankCard;
import com.epam.dto.DebitBankCard;
import com.epam.dto.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Unit test for simple App.
 */
public class BankImplTest {

    @org.junit.jupiter.api.Test
    public void testCreateCreditBankCard() {
        User user = new User("John", "Doe", java.time.LocalDate.of(1990, 1, 1));
        BankImpl bank = new BankImpl();

        BankCard card = bank.createBankCard(user, BankCardType.CREDIT);

        assertInstanceOf(CreditBankCard.class, card);
        assertEquals(user, card.getUser());
        assertEquals(16, card.getNumber().length());
        assertEquals(10000.0, ((CreditBankCard) card).getCreditLimit(), 0.001);
    }

    @Test
    public void testCreateDebitBankCard() {
        User user = new User("Jane", "Doe", java.time.LocalDate.of(1995, 5, 15));
        BankImpl bank = new BankImpl();

        BankCard card = bank.createBankCard(user, BankCardType.DEBIT);

        assertInstanceOf(DebitBankCard.class, card);
        assertEquals(user, card.getUser());
        assertEquals(16, card.getNumber().length());
        assertEquals(0.0, ((DebitBankCard) card).getBalance(), 0.001);
    }
}

