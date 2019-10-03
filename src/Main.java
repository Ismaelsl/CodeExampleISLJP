import classes.Instruction;
import classes.InstructionManagement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InstructionManagement management = new InstructionManagement();
        List<Instruction> instructionsList = management.instructionsGeneratorForTesting();
        for (Instruction instruction: instructionsList) {
            management.instructionExecutor(instruction);
        }
        management.reportGenerator();
    }
}
