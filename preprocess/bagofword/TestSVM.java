package preprocess.bagofword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import preprocess.NLP.RDRsegmenter;
import preprocess.bagofword.Dict;

public class TestSVM {
	public static void main(String[] args) throws IOException {
		RDRsegmenter segmenter = new RDRsegmenter();

		List<String> segString = new ArrayList<String>();
		List<String> segString1 = new ArrayList<String>();
		List<String> segString2 = new ArrayList<String>();
		List<String> segString0 = new ArrayList<String>();
		
		int label = 1;
//		String str1 = "The quick brown fox jumps over the lazy dog and"; 		
//		String str2 = "Never jump over the lazy dog quickly";
//		
//		segString0.add(str1);
//		segString0.add(str2);
		segString1 = ReadDB.readFile("ban-hang");
		segString0.addAll(segString1);
		segString1 = ReadDB.readFile("xay-dung");
		segString0.addAll(segString1);
		segString1 = ReadDB.readFile("kien-truc");
		segString0.addAll(segString1);
		segString1 = ReadDB.readFile("ngan-hang");
		segString0.addAll(segString1);
		segString1 = ReadDB.readFile("co-khi-che-tao");
		segString0.addAll(segString1);
		segString1 = ReadDB.readFile("du-lich");
		segString0.addAll(segString1);
		segString1 = ReadDB.readFile("giao-duc");
		segString0.addAll(segString1);
		
		for (String string : segString0) {
			string = RemoveSB.removeSB(string);
			segString2.add(string.toLowerCase());
		}

		for (String string : segString2) {
			string = segmenter.segmentRawString(string);
			boolean check = CheckVietNameseDocs.checkVnDoc(string);
			if (check == true) {
				segString.add(string);
			}			
		}
			
		List<String> data = new ArrayList<String>();
		for (String string : segString) {
			data.addAll(RemoveStopWord.removeStopWord(string));
		}
		
		List<String> dict = new ArrayList<String>();
		dict = Dict.createDict(data);
		
		List<Double> idf = new ArrayList<Double>();
		idf = Vector.IdfCalculating(segString, dict);
		
		String TokenIdf = "";
		for (int i = 0; i < dict.size(); i++) {
			TokenIdf = TokenIdf + dict.get(i) + " - " + idf.get(i) + "\n";
		}
		WriteFile.writeFile("src/preprocess/data/TokenIdf.txt", TokenIdf);
		
		String allNode = "";
		
		for (int i = 0; i < segString.size(); i++) {
			String node = Vector.NodeCalculating(idf, dict, segString.get(i));
			allNode = allNode + label + " " + node + "\n";	
		}
		WriteFile.writeFile("src/preprocess/data/traindata.txt", allNode);
	}
	
	
}

