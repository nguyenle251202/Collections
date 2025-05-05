package handles;

import manager.Dictionary;
import java.util.Scanner;

public class SynonymHandler {
    private final Dictionary dictionary;

    public SynonymHandler(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter word to add synonym to: ");
        String word = scanner.nextLine();

        if (dictionary.searchWord(word) == null) {
            System.out.println("Word not found in dictionary.");
            return;
        }

        System.out.println("Enter synonym: ");
        String synonym = scanner.nextLine();
        dictionary.addSynonym(word, synonym);
        System.out.println("Synonym added successfully.");
    }
}