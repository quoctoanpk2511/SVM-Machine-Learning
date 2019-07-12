package demoSVM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import preprocess.NLP.RDRsegmenter;
import preprocess.trainning.ReadFile;
import preprocess.trainning.RemoveSB;
import preprocess.trainning.RemoveStopWord;
import preprocess.trainning.Vector;
import preprocess.trainning.WriteFile;

public class Classification {
//	public static void main(String[] args) throws IOException {
	public static String Classifier(String string) throws IOException {
		RDRsegmenter segmenter = new RDRsegmenter();
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

		String node = Vector.NodeCalculating(idf, dict, string);
		WriteFile.writeFile("src/demoSVM/predict.txt", node);
		return node;
	}
}
