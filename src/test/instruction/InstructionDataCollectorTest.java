package test.instruction;

import classes.currency.Currency;
import classes.currency.CurrencyGBP;
import classes.instruction.Instruction;
import classes.instruction.InstructionDataCollector;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class InstructionDataCollectorTest {
    InstructionDataCollector instructionDataCollector;

    @org.junit.jupiter.api.BeforeEach
    public void beforeEachTestMethod() {
        instructionDataCollector = new InstructionDataCollector();
    }

    @org.junit.jupiter.api.Test
    void currencyChecker() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = InstructionDataCollector.class.getDeclaredMethod("currencyChecker", String.class);
        method.setAccessible(true);
        Currency currency = (Currency) method.invoke(instructionDataCollector, "GBP");
        Assert.assertEquals("GBP", currency.getType());
        Assert.assertTrue(CurrencyGBP.class.isInstance(currency));
    }

    @org.junit.jupiter.api.Test
    void inputChecker() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = InstructionDataCollector.class.getDeclaredMethod("inputChecker", Scanner.class, boolean.class);
        method.setAccessible(true);
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);
        String input = (String) method.invoke(instructionDataCollector, scanner, true);
        Assert.assertEquals("5",input);
    }

    @org.junit.jupiter.api.Test
    void instructionDataScannerCollector(){
        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(in);
        CurrencyGBP currencyGBP = new CurrencyGBP();
        Instruction instruction = new Instruction("JP Morgan", true, 5.0, currencyGBP,
                "25/10/2019","28/10/2019", scanner.nextInt(), 500);
        Assert.assertTrue(instruction.getUnits() == 5);
    }
}
