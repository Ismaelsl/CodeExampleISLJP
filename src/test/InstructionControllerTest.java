package test;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import classes.currency.Currency;
import classes.instruction.Instruction;
import classes.instruction.InstructionController;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.util.List;


class InstructionControllerTest {
    InstructionController instructionController = new InstructionController();
    List<Instruction> instructionList;
    Logger instructionLogger = (Logger) LoggerFactory.getLogger(InstructionController .class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();


    @org.junit.jupiter.api.BeforeEach
    public void beforeEachTestMethod() {
        instructionList = instructionController.instructionsGeneratorForTesting();
    }

    @org.junit.jupiter.api.Test
    void setNextWorkingDay() {
        listAppender.start();
        instructionLogger.addAppender(listAppender);
        //Happy path
        Instruction instructionMonday = instructionController.setNextWorkingDay(instructionList.get(3));
        Assert.assertTrue(instructionMonday.getInstructionDate().equals("14/10/2019"));
        //Unhappy path
        Instruction instructionSunday = instructionController.setNextWorkingDay(instructionList.get(1));
        Assert.assertFalse(instructionSunday.getInstructionDate().equals("05/10/2019"));
        //Normal path
        Instruction instructionNoDateChanged = instructionController.setNextWorkingDay(instructionList.get(5));
        Assert.assertFalse(instructionSunday.getInstructionDate().equals("10/10/2019"));
        //Throw error
        Instruction wrongErrorInstruction = new Instruction("TSB", false, 21.50,
                new Currency("AED"), "XX/10/2019", "17/10/2019", 3000, 100.5);
        List<ILoggingEvent> logsList = listAppender.list;
        wrongErrorInstruction = instructionController.setNextWorkingDay(wrongErrorInstruction);
        Assert.assertTrue(logsList.get(0).getMessage().contains("An error happened while parsing the date"));
        Assert.assertFalse(logsList.get(0).getMessage().contains("Wrong error message"));

    }
}