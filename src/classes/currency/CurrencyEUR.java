package classes.currency;

import classes.instruction.Instruction;

public class CurrencyEUR extends Currency {
    //A little bit redundant to create a constant here but wanted to show the usage of at least a couple
    public final static String EUR = "EUR";

    public CurrencyEUR() {
        super(EUR);
    }

    /**
     * An example of overriding a method, I am just adding +5 to make some difference from the father class
     *
     * @param instruction
     * @return
     */
    @Override
    public double calculateTotal(Instruction instruction) {
        return instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFix() + 5;
    }
}
