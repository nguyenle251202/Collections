package model;

import java.util.ArrayList;
import java.util.List;

public class WordData {
    private String word;
    private String meaning;
    private List<String> synonyms;

    // Contructor -----------------------------------------------------------
    public WordData(String word, String meaning, List<String> synonyms) {
        this.word = word;
        this.meaning = meaning;
        this.synonyms = synonyms != null ? synonyms : new ArrayList<>();
    }
    // ----------------------------------------------------------------------

    // Getters and Setters --------------------------------------------------
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public void addSynonym(String synonym) {
        if (!synonyms.contains(synonym)) {
            synonyms.add(synonym);
        }
    }
    // ----------------------------------------------------------------------

    // toString() ------------------------------------------------------------
    @Override
    public String toString() {
        return "Word: " + word +
                "\nMeaning: " + meaning +
                "\nSynonyms: " + String.join(", ", synonyms);
    }
    // ----------------------------------------------------------------------
}