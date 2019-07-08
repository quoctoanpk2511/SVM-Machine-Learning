package preprocess.bagofword;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReadFile {
	public static Map<String, Double> readFile(String inFilePath) throws IOException {

    	try(BufferedReader br = Files.newBufferedReader(Paths.get(inFilePath))) {
    		List<String> listStr = new ArrayList<String>();
    		String line;
            while ((line = br.readLine()) != null) {
                listStr.add(line);
            }
           
            Map<String, Double> TokenIdfMap = new TreeMap<String, Double>();
            
            for (int i = 0; i < listStr.size(); i++) {
				String [] word = listStr.get(i).split(" - ");
				TokenIdfMap.put(word[0], Double.valueOf(word[1]));
			}
            return TokenIdfMap;
    	}
	}
}
