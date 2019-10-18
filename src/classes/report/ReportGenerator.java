package classes.report;

import classes.currency.Currency;
import classes.instruction.Instruction;
import classes.instruction.InstructionController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportGenerator {
    private ArrayList<Instruction> buyInstructionList;
    private ArrayList<Instruction> sellInstructionList;

    public ReportGenerator() {
        buyInstructionList = new ArrayList<>();
        sellInstructionList = new ArrayList<>();
    }

    /**
     * Method to call the report formatting, I am using this method to avoid code repetitions and call with
     * two different arrays (BUY and SELL)
     */
    public void generateReport() {
        reportStringFormatting(buyInstructionList, "BUY");
        reportStringFormatting(sellInstructionList, "SELL");
    }

    /**
     * Method which format the string to show in the screen with the summary
     *
     * @param instructionsList
     * @param typeOfBusiness
     */
    public void reportStringFormatting(ArrayList<Instruction> instructionsList, String typeOfBusiness) {
        Collections.reverse(instructionsList);
        final String[] reportFormatted = {"Report list for " + typeOfBusiness + " is empty"};
        final int[] counter = {1};
        if (!instructionsList.isEmpty()) {
            if (typeOfBusiness.equals("SELL")) reportFormatted[0] = "Outgoing list \n";
            else reportFormatted[0] = "Incoming list \n";
            instructionsList.stream().map(instruction -> {
                reportFormatted[0] += "Rank : " + counter[0] + "\n" + typeOfBusiness + " : " + instruction + "\n";
                counter[0]++;
                return instruction;
            }).collect(toList());

        }
        System.out.println(reportFormatted[0]);
    }

    public void instructionSummarizer(List<Instruction> instructionsList) {
        InstructionController management = new InstructionController();
        if (!instructionsList.isEmpty()) {
            instructionsList.stream().map(instruction -> {
                Currency currency = instruction.getCurrency();
                instruction = management.setNextWorkingDay(instruction);
                instruction.setTotalUSD(currency.calculateTotal(instruction));
                if (!instruction.isSelling()) buyInstructionList.add(instruction);
                else sellInstructionList.add(instruction);
                return instruction;
            }).collect(toList());
        }
    }

    public ArrayList<Instruction> getBuyInstructionList() {
        return buyInstructionList;
    }

    public void setBuyInstructionList(ArrayList<Instruction> buyInstructionList) {
        this.buyInstructionList = buyInstructionList;
    }

    public ArrayList<Instruction> getSellInstructionList() {
        return sellInstructionList;
    }

    public void setSellInstructionList(ArrayList<Instruction> sellInstructionList) {
        this.sellInstructionList = sellInstructionList;
    }

}
