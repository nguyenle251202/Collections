package handles;

import manager.Dictionary;
import manager.TranslatorService;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class TranslateHandler {
    private final TranslatorService translatorService;
    private final Dictionary dictionary;

    public TranslateHandler(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.translatorService = new TranslatorService(dictionary);
    }

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to translate (Vietnamese to English): ");
        String text = scanner.nextLine();

        try {
            String translatedText = translatorService.translate(text, "vi", "en");
            System.out.println("Translated: " + translatedText);

            // Tự động lưu vào dictionary nếu chưa có
            if (!dictionary.containsWord(text)) {
                dictionary.addWord(text, translatedText, new ArrayList<>());
                System.out.println("The translation has been saved to dictionary.");
            }
        } catch (Exception e) {
            System.out.println("Translation failed: " + e.getMessage());
        }
    }
}