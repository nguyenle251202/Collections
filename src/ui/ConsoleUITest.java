package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ConsoleUI;
import manager.Dictionary;
import handles.*;
import enums.Enum;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUITest {

    private ConsoleUI consoleUI;
    private Dictionary dictionary;
    private TranslateHandler translateHandler;
    private SearchHandler searchHandler;
    private HistoryHandler historyHandler;
    private SynonymHandler synonymHandler;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        dictionary = new Dictionary();
        dictionary.addWord("hello", "a greeting", List.of("hi", "greetings"));
        dictionary.addWord("world", "the earth", List.of("earth", "globe"));
        dictionary.addWord("java", "a programming language", List.of("jvm", "jdk"));

        translateHandler = new TranslateHandler(dictionary);
        searchHandler = new SearchHandler(dictionary);
        historyHandler = new HistoryHandler(dictionary);
        synonymHandler = new SynonymHandler(dictionary);

        consoleUI = new ConsoleUI(); // This will initialize the dictionary again, but it's fine for testing.
        consoleUI.dictionary.clear(); // Clear the sample data to avoid conflicts.
        consoleUI.dictionary.addWord("hello", "a greeting", List.of("hi", "greetings"));
        consoleUI.dictionary.addWord("world", "the earth", List.of("earth", "globe"));
        consoleUI.dictionary.addWord("java", "a programming language", List.of("jvm", "jdk"));

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testDisplayMenu() {
        ConsoleUI.displayMenu();
        String expectedOutput = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testRun_TranslateOption() {
        String input = "1\nhello\n5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutputStart = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "----- Translate -----" + System.lineSeparator() +
                "Enter the word to translate: " +
                "Translation of hello: a greeting" + System.lineSeparator() +
                "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }

    @Test
    void testRun_SearchOption() {
        String input = "2\nhe\n5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutputStart = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "----- Search -----" + System.lineSeparator() +
                "Enter the search term: " +
                "Search results for 'he':" + System.lineSeparator() +
                "hello - a greeting" + System.lineSeparator() +
                "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }

    @Test
    void testRun_HistoryOption() {
        String input = "3\n5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutputStart = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "----- History -----" + System.lineSeparator() +
                "Search History:" + System.lineSeparator() +
                "No history available." + System.lineSeparator() +
                "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }

    @Test
    void testRun_AddSynonymOption() {
        String input = "4\nhello\nnew_synonym\n5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutputStart = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "----- Synonyms -----" + System.lineSeparator() +
                "Enter the word to add a synonym to: " +
                "Enter the new synonym: " +
                "Synonym 'new_synonym' added to 'hello'." + System.lineSeparator() +
                "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }

    @Test
    void testRun_ExitOption() {
        String input = "5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutput = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testRun_InvalidOption() {
        String input = "6\n5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutputStart = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Invalid option" + System.lineSeparator() +
                "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }

    @Test
    void testRun_InvalidInputThenValid() {
        String input = "abc\n1\nhello\n5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        consoleUI.run();

        String expectedOutputStart = "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Invalid input. Please enter a number between 1 and 5." + System.lineSeparator() +
                "Enter your option (1-5): " +
                "----- Translate -----" + System.lineSeparator() +
                "Enter the word to translate: " +
                "Translation of hello: a greeting" + System.lineSeparator() +
                "\n===== Main Menu =====" + System.lineSeparator() +
                "[1]. Translate" + System.lineSeparator() +
                "[2]. Search" + System.lineSeparator() +
                "[3]. History" + System.lineSeparator() +
                "[4]. Add Synonym" + System.lineSeparator() +
                "[5]. Exit" + System.lineSeparator() +
                "Enter your option (1-5): " +
                "Exiting..." + System.lineSeparator() +
                "Application finished." + System.lineSeparator();

        String actualOutput = outputStream.toString();
        assertTrue(actualOutput.startsWith(expectedOutputStart));
    }
}