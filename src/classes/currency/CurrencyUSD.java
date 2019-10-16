package classes.currency;

import classes.instruction.Instruction;

public class CurrencyUSD extends Currency {
    //A little bit redundant to create a constant here but wanted to show the usage of at least a couple
    public final static String USD = "USD";
    public CurrencyUSD() {
        super(USD);
    }

    /**
     * Calling the father method since in the case of USD the formula is the default
     *
     * @param instruction
     * @return
     */
    public double calculateTotal(Instruction instruction) {
        return super.calculateTotal(instruction);
    }
}
