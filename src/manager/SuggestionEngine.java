package manager;

import java.util.ArrayList;
import java.util.List;

public class SuggestionEngine {
    private final Dictionary dictionary;
    private static final int MAX_DISTANCE = 2;

    public SuggestionEngine(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getSuggestions(String input) {
        List<String> suggestions = new ArrayList<>();
        for (String word : dictionary.wordMap.keySet()) {
            if (calculateDistance(input.toLowerCase(), word) <= MAX_DISTANCE) {
                suggestions.add(dictionary.wordMap.get(word).getWord());
            }
        }
        return suggestions;
    }

    private int calculateDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}