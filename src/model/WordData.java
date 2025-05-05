package model;

import java.util.List;

public class WordData {
    private String Word;
    private String Meaning;
    private List<String> Synonyms;

    // Contructor ---------------------------------------------------------
    public WordData(String word, String meaning, List<String> synonyms) {
        Word = word;
        Meaning = meaning;
        Synonyms = synonyms;
    }
    // --------------------------------------------------------------------
    // Getter -------------------------------------------------------------
    public String getWord() {
        return Word;
    }
    public String getMeaning() {
        return Meaning;
    }
    public List<String> getSynonyms() {
        return Synonyms;
    }
    // --------------------------------------------------------------------

    // Setter -------------------------------------------------------------
    public void setWord(String word) {
        this.Word = word;
    }

    public void setMeaning(String meaning) {
        this.Meaning = meaning;
    }

    public void setSynonyms(List<String> synonyms) {
        this.Synonyms = synonyms;
    }
    // --------------------------------------------------------------------
    // toString -----------------------------------------------------------
    @Override
    public String toString() {
        return "Word{" +
                "word='" + Word + '\'' +
                ", meaning='" + Meaning + '\'' +
                ", synonyms=" + Synonyms +
                '}';
    }
    // --------------------------------------------------------------------

}

