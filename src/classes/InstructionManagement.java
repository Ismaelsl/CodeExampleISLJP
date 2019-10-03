package classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class to manage the instructions, date validation, USD calculation, and report generation
 */
public class InstructionManagement {
    ReportGenerator reportAdd = new ReportGenerator();
    Calendar masterCalendar;
    int nextWorkingDay = 0;
    
    public InstructionManagement(){
        masterCalendar = Calendar.getInstance();
    }

    public void instructionExecutor(Instruction instruction){
        if(instruction != null) {
            nextWorkingDay(instruction);
        }
    }

    /**
     * Class that is checking which type of currency we have and base on that will check if the date for the instruction
     * it is weekend of weekday in the case of be a weekend the next working day will be selected
     * @param instruction
     */
    public void nextWorkingDay(Instruction instruction){
        nextWorkingDay = 0;
        if(instruction.getCurrency().toUpperCase().equals("AED") ||
                instruction.getCurrency().toUpperCase().equals("SAR")){
            nextWorkingDay = Calendar.SUNDAY;
        }else{
            nextWorkingDay = Calendar.MONDAY;
        }
        try {
            Date parsingDateForNextWorkingDay=new SimpleDateFormat("dd/MM/yyyy").parse(instruction.getInstructionDate());
            masterCalendar.setTime(parsingDateForNextWorkingDay);
            if(nextWorkingDay == Calendar.SUNDAY){
                if(masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ||
                        masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    masterCalendar.add(Calendar.DATE,nextWorkingDay);
                }
            }else if(nextWorkingDay == Calendar.MONDAY){
                if(masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                        masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    masterCalendar.add(Calendar.DATE,nextWorkingDay);
                }
            }
            DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            instruction.setInstructionDate(simpleDateFormat.format(masterCalendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        USDCalculator(instruction);
    }

    public void USDCalculator (Instruction instruction){
        instruction.setTotalUSD(instruction.getPricePerUnit()*instruction.getUnits()*instruction.getAgreedFix());
        reportAdd.addInstructionToReport(instruction);
    }

    public void reportGenerator(){
        reportAdd.generateReport();
    }

    public List<Instruction> instructionsGeneratorForTesting(){
        List<Instruction>instructionsList = new ArrayList<>();
        Instruction firstInstruction = new Instruction("Morgan", true, 0.50,
                "SAR", "01/10/2019", "04/10/2019", 450, 750.5);
        Instruction secondInstruction = new Instruction("Spanish Bank", false, 3.50,
                "AED", "04/10/2019", "04/10/2019", 1750, 150.5);
        Instruction thirdInstruction = new Instruction("JP Morgan", false, 1.50,
                "SAR", "05/10/2019", "07/10/2019", 45000, 1500.5);
        Instruction fourthInstruction = new Instruction("Bank of China", false, 5.50,
                "SGP", "12/10/2019", "15/10/2019", 5000, 4500.5);
        Instruction fifthInstruction = new Instruction("Irish Bank", true, 8.50,
                "GBP", "13/10/2019", "17/10/2019", 25000, 200.5);
        Instruction sixInstruction = new Instruction("Latvia Bank", true, 10.50,
                "EUR", "10/10/2019", "17/10/2019", 100, 10.5);
        Instruction seventhInstruction = new Instruction("Santander", false, 15.50,
                "EUR", "11/10/2019", "17/10/2019", 450000, 500.5);
        Instruction eigthInstruction = new Instruction("TSB", false, 21.50,
                "AED", "11/10/2019", "17/10/2019", 3000, 100.5);

        instructionsList.add(firstInstruction);
        instructionsList.add(secondInstruction);
        instructionsList.add(thirdInstruction);
        instructionsList.add(fourthInstruction);
        instructionsList.add(fifthInstruction);
        instructionsList.add(sixInstruction);
        instructionsList.add(seventhInstruction);
        instructionsList.add(eigthInstruction);
        return instructionsList;
    }
}
