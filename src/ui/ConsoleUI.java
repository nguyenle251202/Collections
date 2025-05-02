package ui;

import enums.Enum;

import java.util.Scanner;

public class ConsoleUI {
    // OptionMenu -----------------------------------------
    public static enums.Enum.Option OptionMenu(int numberOption) {
        enums.Enum.Option option = Enum.Option.fromCode(numberOption);
        if (option == null) {
            System.out.println("Invalid option");
            return option;
        }
        switch (option) {
            case TRANSLATE:
                System.out.println("----- Translate -----");
                break;
            case SEARCH:
                System.out.println("----- Search -----");
                break;
            case HISTORY:
                System.out.println("----- History -----");
                break;
            case EXIT:
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        return option;
    }
    // ----------------------------------------------------
    // DISPLAY MENU ---------------------------------------
    public static void displayMenu() {
        System.out.println("");
        System.out.println("===== Main Menu =====");
        System.out.println("[1]. Translate");
        System.out.println("[2]. Search");
        System.out.println("[3]. History");
        System.out.println("[4]. Add Synonym");
        System.out.println("[5]. Exit");
    }
    // ----------------------------------------------------
    public void start() {
        Scanner scanner = new Scanner(System.in);
        Enum.Option chosenOption;
        do {
            displayMenu();
            System.out.print("Enter your option (1-5): ");
            int input = -1;
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                System.out.print("Enter your option (1-5): ");
            }
            input = scanner.nextInt();
            scanner.nextLine();
            chosenOption = OptionMenu(input);
        } while (chosenOption != Enum.Option.EXIT);
        scanner.close();
        System.out.println("Application finished.");
    }
}
