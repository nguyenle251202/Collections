package handles;

import manager.Dictionary;
import java.util.List;
import java.util.Scanner;

public class SearchHandler {
    private final Dictionary dictionary;

    public SearchHandler(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Enter search option:");
            System.out.println("1. Exact word search");
            System.out.println("2. Prefix search");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume invalid input
            }
        }

        if (choice == 1) {
            System.out.println("Enter word to search: ");
            String word = scanner.nextLine();
            model.WordData result = dictionary.searchWord(word);
            if (result != null) {
                System.out.println(result);
            } else {
                List<String> suggestions = dictionary.getSuggestions(word);
                if (!suggestions.isEmpty()) {
                    System.out.println("Word not found. Did you mean:");
                    suggestions.forEach(System.out::println);
                } else {
                    System.out.println("Word not found and no suggestions available.");
                }
            }
        } else { // choice == 2
            System.out.println("Enter prefix to search: ");
            String prefix = scanner.nextLine();
            List<String> results = dictionary.searchByPrefix(prefix);
            if (!results.isEmpty()) {
                System.out.println("Words starting with '" + prefix + "':");
                results.forEach(System.out::println);
            } else {
                System.out.println("No words found with this prefix.");
            }
        }
    }
}
