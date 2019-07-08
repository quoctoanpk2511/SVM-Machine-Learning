package preprocess.bagofword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import preprocess.NLP.RDRsegmenter;

public class Classification {
//	public static void Classifi(String string) throws IOException {
	public static void main(String[] args) throws IOException {
		
		RDRsegmenter segmenter = new RDRsegmenter();
		String string = "aaaaaaaaaaaa bbbbbbbb";
		string = RemoveSB.removeSB(string);
		string = segmenter.segmentRawString(string);
		string = String.join(" ", RemoveStopWord.removeStopWord(string));
		Map<String, Double> TokenIdfMap = ReadFile.readFile("src/preprocess/data/TokenIdf.txt");
		List<String> dict = new ArrayList<String>();
		List<Double> idf = new ArrayList<Double>();
		for (String str : TokenIdfMap.keySet()) {
			dict.add(str);
			idf.add(TokenIdfMap.get(str));
		}
		String string1 = "a abbott adayroi admin";
		String node = Vector.NodeCalculating(idf, dict, string1);
		System.out.println(node);
		WriteFile.writeFile("src/preprocess/data/predict.txt", node);
		
	}
}
