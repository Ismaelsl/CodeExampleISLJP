package classes;

import classes.instruction.Instruction;
import classes.instruction.InstructionController;
import classes.instruction.InstructionDataCollector;
import classes.report.ReportGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InstructionDataCollector collector = new InstructionDataCollector();
        List<Instruction> instructionsList = new ArrayList<>();
        InstructionController management = new InstructionController();
        Scanner scanner = new Scanner(System.in);
        boolean instructionAutomaticGeneration = true;
        int selection = 1;
        while (selection != 4) {
            System.out.print("Please choose an option from the menu: \n");
            System.out.print("1) Add a new Instruction. \n" +
                    "2) Generate instructions automatically (only valid once). \n" +
                    "3) Generate Report. \n" +
                    "4) Exit. \n");
            System.out.print("Please enter the option you want from the menu: ");
            selection = scanner.nextInt();
            switch (selection) {
                case 1:
                    Instruction instruction = collector.instructionDataScannerCollector();
                    instructionsList.add(instruction);
                    System.out.println("New instruction " + instruction.getEntityName() + " added successfully");
                    break;
                case 2:
                    if (instructionAutomaticGeneration) {
                        System.out.println("Generation new instructions automatically");
                        instructionsList.addAll(management.instructionsGeneratorForTesting());
                        instructionAutomaticGeneration = false;
                        System.out.println("New instructions were added successfully");
                    } else {
                        System.out.println("Sorry only once automatic generation is allowed");
                    }
                    break;
                case 3:
                    System.out.println("Generating report");
                    ReportGenerator instructionReport = new ReportGenerator();
                    instructionReport.instructionSummarizer(instructionsList);
                    instructionReport.generateReport();
                    break;
            }
        }
        System.out.println("Closing instruction management system");
        System.exit(0);
    }
}
