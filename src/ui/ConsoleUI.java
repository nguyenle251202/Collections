package ui;

import enums.Enum;
import handles.*;
import manager.Dictionary;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    final Dictionary dictionary;
    private final TranslateHandler translateHandler;
    private final SearchHandler searchHandler;
    private final HistoryHandler historyHandler;
    private final SynonymHandler synonymHandler;

    public ConsoleUI() {
        this.dictionary = new Dictionary();
        initializeSampleData();
        this.translateHandler = new TranslateHandler(dictionary);
        this.searchHandler = new SearchHandler(dictionary);
        this.historyHandler = new HistoryHandler(dictionary);
        this.synonymHandler = new SynonymHandler(dictionary);
    }

    private void initializeSampleData() {
        dictionary.addWord("tý", "mouse", List.of("rat", "ratatouille"));
        dictionary.addWord("sửu", "buffalo", List.of("ox", "bovine"));
        dictionary.addWord("dần", "tiger", List.of("big cat", "predator"));
        dictionary.addWord("mão", "cat", List.of("kitten", "feline"));
        dictionary.addWord("thìn", "dragon", List.of("mythical beast", "serpent"));
    }

    public static void displayMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("[1]. Translate");
        System.out.println("[2]. Search");
        System.out.println("[3]. History");
        System.out.println("[4]. Add Synonym");
        System.out.println("[5]. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Enum.Option chosenOption;

        do {
            displayMenu();
            System.out.print("Enter your option (1-5): ");

            // Xử lý input
            int input = -1;
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                System.out.print("Enter your option (1-5): ");
            }
            input = scanner.nextInt();
            scanner.nextLine();

            chosenOption = Enum.Option.fromCode(input);
            if (chosenOption == null) {
                System.out.println("Invalid option");
                continue;
            }

            switch (chosenOption) {
                case TRANSLATE:
                    System.out.println("----- Translate -----");
                    translateHandler.handle();
                    break;
                case SEARCH:
                    System.out.println("----- Search -----");
                    searchHandler.handle();
                    break;
                case HISTORY:
                    System.out.println("----- History -----");
                    historyHandler.handle();
                    break;
                case ADDSYNONYMS:
                    System.out.println("----- Synonyms -----");
                    synonymHandler.handle();
                    break;
                case EXIT:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        } while (chosenOption != Enum.Option.EXIT);

        scanner.close();
        System.out.println("Application finished.");
    }

    public static void main(String[] args) {
        new ConsoleUI().run();
    }
}