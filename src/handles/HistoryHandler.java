package handles;

import manager.Dictionary;
import java.util.List;

public class HistoryHandler {
    private final Dictionary dictionary;

    public HistoryHandler(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void handle() {
        List<String> history = dictionary.getHistory();
        if (history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            System.out.println("Recent search history:");
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }
    }
}