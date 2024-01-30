import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Exercise44 {
    public static List<String> getWords(String inputFileName) {
        List<String> wordsList = new ArrayList<>();
        In in = new In(inputFileName);
        while(!in.isEmpty()) {
            wordsList.add(in.readString());
        }
        return wordsList;
    }

    public static int countUniqueWords(List<String> wordsList) {
        Set<String> wordsSet = new  HashSet<>();
        for(String word : wordsList) {
            wordsSet.add(word);
        }
        return wordsSet.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> targets, List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : targets) {
            map.put(word, 0);
        }
        for (String s : targets) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            }
        }
        return map;
    }
}
