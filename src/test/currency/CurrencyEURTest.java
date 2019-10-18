package test.currency;

import classes.currency.Currency;
import classes.currency.CurrencyEUR;
import classes.instruction.Instruction;
import org.junit.Assert;

public class CurrencyEURTest {

    Instruction instruction;
    Currency currency;

    @org.junit.jupiter.api.BeforeEach
    public void beforeEachTestMethod() {
        currency = new CurrencyEUR();
        instruction = new Instruction("Morgan", true, 0.50,
                currency, "01/10/2019", "04/10/2019", 450, 750.5);
    }

    @org.junit.jupiter.api.Test
    void calculateTotal() {
        double totalUSD = currency.calculateTotal(instruction);
        Assert.assertTrue(totalUSD == 168867.5);
    }
}
