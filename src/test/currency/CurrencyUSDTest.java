package test.currency;

import classes.currency.Currency;
import classes.currency.CurrencyUSD;
import classes.instruction.Instruction;
import org.junit.Assert;

public class CurrencyUSDTest {
    Instruction instruction, instructionSAR;
    Currency currency;

    @org.junit.jupiter.api.BeforeEach
    public void beforeEachTestMethod() {
        currency = new CurrencyUSD();
        instruction = new Instruction("Morgan", true, 0.50,
                new CurrencyUSD(), "01/10/2019", "04/10/2019", 450, 750.5);
        instructionSAR = new Instruction("Morgan", true, 0.50,
                new Currency("SAR"), "01/10/2019", "04/10/2019", 450, 750.5);
    }

    @org.junit.jupiter.api.Test
    void calculateTotal() {
        double totalUSD = currency.calculateTotal(instruction);
        double totalUSDSAR = currency.calculateTotal(instructionSAR);
        Assert.assertTrue(totalUSD == totalUSDSAR);
    }
}
