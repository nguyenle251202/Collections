package manager;

import java.util.*;

public class HistoryManager {
    private final int maxSize;
    private final LinkedList<String> history;

    public HistoryManager(int maxSize) {
        this.maxSize = maxSize;
        this.history = new LinkedList<>();
    }

    public void addToHistory(String word) {
        history.remove(word); // Remove if already exists to avoid duplicates
        history.addFirst(word);
        if (history.size() > maxSize) {
            history.removeLast();
        }
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}