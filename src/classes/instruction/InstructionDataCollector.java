package classes.instruction;

import classes.currency.Currency;
import classes.currency.CurrencyEUR;
import classes.currency.CurrencyGBP;
import classes.currency.CurrencyUSD;

import java.util.Scanner;

public class InstructionDataCollector {

    /**
     * Method to collect data from the line prompt
     * I tried to reduce the repetition of code as much as possible
     * @return
     */
    public Instruction instructionDataCollector() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Entity name: ");
        String entityName = scanner.next();
        System.out.print("Are you selling? (1: Yes, any other number: No): ");
        boolean isSelling = false;
        String input = inputChecker(scanner,true);
        int selling = Integer.parseInt(input);
        if (selling <= 0) isSelling = true;
        System.out.print("Enter agreed Fix: ");
        input = inputChecker(scanner,false);
        double agreedFix = Double.parseDouble(input);
        System.out.print("Enter Currency type: ");
        String currencyType = scanner.next();
        //Here I am creating the father class that could be initialised with any child class in the currencyChecker class
        Currency currency = currencyChecker(currencyType);
        System.out.print("Enter Instruction Date (dd/MM/yyyy): ");
        String instructionDate = scanner.next();
        System.out.print("Enter Settlement Date (dd/MM/yyyy): ");
        String settlementDate = scanner.next();
        System.out.print("Enter number of units: ");
        input = inputChecker(scanner,true);
        int units = Integer.parseInt(input);
        System.out.print("Enter price per units: ");
        input = inputChecker(scanner,true);
        int pricePerUnits = Integer.parseInt(input);
        Instruction instruction = new Instruction(entityName, isSelling, agreedFix, currency, instructionDate,
                settlementDate, units, pricePerUnits);
        return instruction;
    }

    /**
     * This method it is used to see which currency subclass will the instruction have
     * @param currencyType
     * @return
     */
    private Currency currencyChecker(String currencyType) {
        Currency currency;
        switch (currencyType.toUpperCase()) {
            case "EUR":
                currency = new CurrencyEUR();
                break;
            case "GBP":
                currency = new CurrencyGBP();
                break;
            case "USD":
                currency = new CurrencyUSD();
                break;
            default:
                currency = new Currency(currencyType);
        }
        return currency;
    }

    /**
     * Basic checker that will test if the data entered is a number or not
     * @param scanner
     * @param isInt this param take care of which parseable method call int or double
     * @return
     */
    private static String inputChecker(Scanner scanner, boolean isInt) {
        String input = "";
        while (true) {
            input = scanner.next();
            if(isInt) {
                if (isNoNegative(input) && isParseableInt(input)) {
                    break;
                }
                System.out.println("ENTER ONLY POSITIVE NUMBERS, NO DECIMALS, NO CHARACTERS");
            }else{
                if (isNoNegative(input) && isParseableDouble(input)) {
                    break;
                }
                System.out.println("ENTER ONLY POSITIVE NUMBERS, NO CHARACTERS");
            }
            System.out.print("Please try again: ");
        }
        return input;
    }

    /**
     * Method used by inputChecker to test if the data entered is int or not
     * @param str
     * @return
     */
    private static boolean isParseableInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Method used by inputChecker to test if the data entered is double or not
     * @param str
     * @return
     */
    private static boolean isParseableDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to check if the number entered is or not negative
     * I am using parseDouble here because it will works with int and double
     * int will add a .0 decimal which will not change the value
     * while if I use parseInt will only work with int
     * @param str
     * @return
     */
    private static boolean isNoNegative(String str) {
        try {
            if(Double.parseDouble(str)<0){
                return false;
            }else {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
