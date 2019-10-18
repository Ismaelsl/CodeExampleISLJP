package test.report;

import classes.currency.Currency;
import classes.currency.CurrencyEUR;
import classes.instruction.Instruction;
import classes.instruction.InstructionController;
import classes.report.ReportGenerator;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReportGeneratorTest {
    InstructionController instructionController;
    Currency currency;
    ReportGenerator reportGenerator;
    ArrayList<Instruction> buyInstructionList;
    ArrayList<Instruction> sellInstructionList;
    List<Instruction> instructionList;


    @org.junit.jupiter.api.BeforeEach
    public void beforeEachTestMethod() {
        buyInstructionList = new ArrayList<>();
        sellInstructionList = new ArrayList<>();
        reportGenerator = new ReportGenerator();
        currency = new CurrencyEUR();
        instructionController = new InstructionController();
        instructionList = instructionController.instructionsGeneratorForTesting();
    }

    @org.junit.jupiter.api.Test
    void reportStringFormatting() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        reportGenerator.instructionSummarizer(instructionList);
        buyInstructionList.addAll(reportGenerator.getBuyInstructionList());
        sellInstructionList = reportGenerator.getSellInstructionList();
        reportGenerator.reportStringFormatting(buyInstructionList, "BUY");
        Assert.assertTrue(!Objects.equals(reportGenerator.getBuyInstructionList(), buyInstructionList));
        Assert.assertTrue(outContent.toString().contains("TSB"));
    }

    @org.junit.jupiter.api.Test
    void instructionSummarizer() {
        reportGenerator.instructionSummarizer(instructionList);
        Assert.assertTrue(!reportGenerator.getBuyInstructionList().isEmpty());
        Assert.assertTrue(!reportGenerator.getSellInstructionList().isEmpty());
    }
}
