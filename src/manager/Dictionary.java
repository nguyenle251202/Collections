package manager;

import model.WordData;
import java.util.*;

public class Dictionary {
    Map<String, WordData> wordMap;
    private HistoryManager historyManager;
    private SuggestionEngine suggestionEngine;

    public Dictionary() {
        wordMap = new HashMap<>();
        historyManager = new HistoryManager(10);
        suggestionEngine = new SuggestionEngine(this);
    }

    public void addWord(String word, String meaning, List<String> synonyms) {
        wordMap.put(word.toLowerCase(), new WordData(word, meaning, synonyms));
    }

    public WordData searchWord(String word) {
        WordData result = wordMap.get(word.toLowerCase());
        if (result != null) {
            historyManager.addToHistory(word);
        }
        return result;
    }

    public List<String> searchByPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        for (String word : wordMap.keySet()) {
            if (word.startsWith(prefix.toLowerCase())) {
                results.add(wordMap.get(word).getWord());
            }
        }
        return results;
    }

    public void addSynonym(String word, String synonym) {
        WordData wordData = wordMap.get(word.toLowerCase());
        if (wordData != null) {
            wordData.addSynonym(synonym);
        }
    }

    public List<String> getHistory() {
        return historyManager.getHistory();
    }

    public List<String> getSuggestions(String input) {
        return suggestionEngine.getSuggestions(input);
    }

    public boolean containsWord(String word) {
        return wordMap.containsKey(word.toLowerCase());
    }
}