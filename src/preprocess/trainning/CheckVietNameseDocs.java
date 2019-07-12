package preprocess.trainning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CheckVietNameseDocs {
	
	// Hàm kiểm tra xem đây có phải văn bản tiếng Việt không
	public static boolean checkVnDoc(String string) throws IOException {
		List<String> Vndict = Files.readAllLines(Paths.get("src/preprocess/StopWord.txt"));
		Set<String> TokenSet = new TreeSet<>();
		double count = 0;
		String[] spl = string.split(" ");
		for (int i = 0; i < spl.length; i++) {
			if(Vndict.contains(spl[i]))
				count++;
			TokenSet.add(spl[i]);
		}

		if (count/TokenSet.size()<0.000001) {
			 return false;
		}
		return true;
		
	}
}
