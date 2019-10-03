package classes;

public class Instruction implements Comparable<Instruction>{
    private String entityName;
    private boolean isSelling;
    private double agreedFix;
    private String currency;
    private String instructionDate;
    private String settlementDate;
    private int units;
    private double pricePerUnit;
    private double totalUSD;

    public Instruction (String entityName, boolean isSelling, double agreedFix,
                        String currency, String instructionDate, String settlementDate,
                        int units, double pricePerUnit){
        this.entityName = entityName;
        this.isSelling = isSelling;
        this.agreedFix = agreedFix;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }


    public double getTotalUSD() {
        return totalUSD;
    }

    public void setTotalUSD(double totalUSD) {
        this.totalUSD = totalUSD;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        this.isSelling = selling;
    }

    public double getAgreedFix() {
        return agreedFix;
    }

    public void setAgreedFix(double agreedFix) {
        this.agreedFix = agreedFix;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(String instructionDate) {
        this.instructionDate = instructionDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return this.getEntityName() + " USD: " + this.getTotalUSD() +
                " instruction Date: " + this.getInstructionDate();
    }

    @Override
    public int compareTo(Instruction instruction) {
        return new Double(this.totalUSD).compareTo(instruction.getTotalUSD());
    }
}
