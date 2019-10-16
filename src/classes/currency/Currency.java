package classes.currency;

import classes.instruction.Instruction;

/**
 * In this case I am assuming that the default way to calculate the total is this one, so I have two options
 * if the currency use the default mode, it will call the father method, if is different, the currency will
 * override the method.
 */
public class Currency {
    private double total;
    private String type;

    public Currency(String type) {
        total = 0.0;
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double calculateTotal(Instruction instruction) {
        return instruction.getPricePerUnit() * instruction.getUnits() * instruction.getAgreedFix();
    }
}
