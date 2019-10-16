package classes.currency;

import classes.instruction.Instruction;

public class CurrencyGBP extends Currency {
    //A little bit redundant to create a constant here but wanted to show the usage of at least a couple
    public final static String GBP = "GBP";

    public CurrencyGBP() {
        super(GBP);
    }

    /**
     * GBP have a different way to calculate the USD so that is why I am overriding the father method
     * @param instruction
     * @return
     */
    @Override
    public double calculateTotal(Instruction instruction) {
        return instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFix() + 1000;
    }
}
