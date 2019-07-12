package preprocess.trainning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dict {
	//Tạo từ điển
	public static List<String> createDict(List<String> data) {
		List<String> dict = new ArrayList<>();		
		Map<String, Integer> wordMap = countWords(data);
		for (String key : wordMap.keySet()) {
			dict.add(key);
        }
		System.out.println(dict.size());
        System.out.println(dict);
		return dict; 		
	}
	
	public static Map<String, Integer> countWords(List<String> input) {
        Map<String, Integer> wordMap = new TreeMap<String, Integer>();
        if (input == null) {
            return wordMap;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
                sb.append(input.get(i));
                addWord(wordMap, sb);
                sb = new StringBuilder();
        }
        addWord(wordMap, sb);
        return wordMap;
    }
	
	
	public static void addWord(Map<String, Integer> wordMap, StringBuilder sb) {
        String word = sb.toString();
        if (word.length() == 0) {
            return;
        }
        if (wordMap.containsKey(word)) {
            int count = wordMap.get(word) + 1;
            wordMap.put(word, count);
        } else {
            wordMap.put(word, 1);
        }
    }
}
