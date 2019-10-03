package classes;

import java.util.ArrayList;
import java.util.Collections;

public class ReportGenerator {
    ArrayList<Instruction> buyInstructionList;
    ArrayList<Instruction> sellInstructionList;
    public ReportGenerator (){
        buyInstructionList = new ArrayList<>();
        sellInstructionList = new ArrayList<>();
    }

    public void addInstructionToReport(Instruction instruction){
        if(!instruction.isSelling()) buyInstructionList.add(instruction);
        else sellInstructionList.add(instruction);
    }

    public void generateReport(){
        reportStringFormatting(buyInstructionList, "BUY");
        reportStringFormatting(sellInstructionList, "SELL");
    }

    public void reportStringFormatting (ArrayList<Instruction>instructionsList, String typeOfBusiness){
        Collections.reverse(instructionsList);
        String reportFormatted = "Report list for " + typeOfBusiness + " is empty";
        int counter = 1;
        if(!instructionsList.isEmpty()){
            if(typeOfBusiness.equals("SELL")) reportFormatted = "Outgoing list \n";
            else reportFormatted = "Incoming list \n";
            for (Instruction instruction: instructionsList) {
                reportFormatted += "Rank : " + counter + "\n" + typeOfBusiness + " : " + instruction + "\n";
                counter++;
            }
        }
        System.out.println(reportFormatted);
    }

}
