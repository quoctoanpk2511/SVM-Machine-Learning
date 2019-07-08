package preprocess.bagofword;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RemoveStopWord {
	//Loại bỏ stop word
	public static List<String> removeStopWord(String segStr) throws IOException {
		
		List<String> data = new ArrayList<String>();
		List<String> stopword = Files.readAllLines(Paths.get("src/preprocess/StopWord.txt"));
		List<String> remove = new ArrayList<String>();
		
		for (String str : segStr.split(" ")) {
			data.add(str.toLowerCase());
		}
		
		for (int i = 0; i < data.size(); i++) {
			if (stopword.contains(data.get(i))) {
				remove.add(data.get(i));
			}
		}
		
		data.removeAll(remove);
		return data;
	}
}
