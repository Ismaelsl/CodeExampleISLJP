package classes.instruction;

import ch.qos.logback.classic.Logger;
import classes.currency.Currency;
import classes.currency.CurrencyEUR;
import classes.currency.CurrencyGBP;
import classes.currency.CurrencyUSD;
import org.slf4j.LoggerFactory;

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
public class InstructionController {
    static final Logger logger = (Logger) LoggerFactory.getLogger(InstructionController .class);
    Calendar masterCalendar;
    int nextWorkingDay = 0;

    public InstructionController() {
        masterCalendar = Calendar.getInstance();
    }

    /**
     * Class that is checking which type of currency we have and base on that will check if the date for the instruction
     * it is weekend of weekday in the case of be a weekend the next working day will be selected
     *
     * @param instruction
     */
    public Instruction setNextWorkingDay(Instruction instruction) {
        if(instruction != null){
            nextWorkingDay = 0;
            if (instruction.getCurrency().getType().toUpperCase().equals("AED") ||
                    instruction.getCurrency().getType().toUpperCase().equals("SAR")) {
                nextWorkingDay = Calendar.SUNDAY;
            } else {
                nextWorkingDay = Calendar.MONDAY;
            }
            try {
                Date parsingDateForNextWorkingDay = new SimpleDateFormat("dd/MM/yyyy").parse(instruction.getInstructionDate());
                masterCalendar.setTime(parsingDateForNextWorkingDay);
                if (nextWorkingDay == Calendar.SUNDAY) {
                    if (masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY ||
                            masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        while(masterCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                            masterCalendar.add(Calendar.DAY_OF_WEEK, 1);
                        }
                    }
                } else if (nextWorkingDay == Calendar.MONDAY) {
                    if (masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                            masterCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        while(masterCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
                            masterCalendar.add(Calendar.DAY_OF_WEEK, 1);
                        }
                    }
                }
                DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                instruction.setInstructionDate(simpleDateFormat.format(masterCalendar.getTime()));
            } catch (ParseException e) {
                logger.info("An error happened while parsing the date " + e);
            }
            return instruction;
        }else{
            logger.error("Instruction is null");
            throw new NullPointerException("Instruction is null");
        }
    }

    /**
     * Method to generate hardcoded instructions that can be used for fast testing of the application
     * @return
     */
    public List<Instruction> instructionsGeneratorForTesting() {
        List<Instruction> instructionsList = new ArrayList<>();
        Instruction firstInstruction = new Instruction("Morgan", true, 0.50,
                new Currency("SAR"), "01/10/2019", "04/10/2019", 450, 750.5);
        Instruction secondInstruction = new Instruction("Spanish Bank", false, 3.50,
                new Currency("AED"), "04/10/2019", "04/10/2019", 1750, 150.5);
        Instruction thirdInstruction = new Instruction("JP Morgan", false, 1.50,
                new Currency("SAR"), "05/10/2019", "07/10/2019", 45000, 1500.5);
        Instruction fourthInstruction = new Instruction("Bank of China", false, 5.50,
                new CurrencyUSD(), "12/10/2019", "15/10/2019", 5000, 4500.5);
        Instruction fifthInstruction = new Instruction("Irish Bank", true, 8.50,
                new CurrencyGBP(), "13/10/2019", "17/10/2019", 25000, 200.5);
        Instruction sixInstruction = new Instruction("Latvia Bank", true, 10.50,
                new CurrencyEUR(), "10/10/2019", "17/10/2019", 100, 10.5);
        Instruction seventhInstruction = new Instruction("Santander", false, 15.50,
                new CurrencyEUR(), "11/10/2019", "17/10/2019", 450000, 500.5);
        Instruction eigthInstruction = new Instruction("TSB", false, 21.50,
                new Currency("AED"), "11/10/2019", "17/10/2019", 3000, 100.5);

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
