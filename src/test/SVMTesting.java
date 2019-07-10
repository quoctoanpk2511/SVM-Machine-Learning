package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import preprocess.NLP.RDRsegmenter;
import preprocess.trainning.CheckVietNameseDocs;
import preprocess.trainning.Document;
import preprocess.trainning.ReadDB;
import preprocess.trainning.ReadFile;
import preprocess.trainning.RemoveSB;
import preprocess.trainning.RemoveStopWord;
import preprocess.trainning.Vector;
import preprocess.trainning.WriteFile;

public class SVMTesting {
	public static void main(String[] args) throws IOException {
		RDRsegmenter segmenter = new RDRsegmenter();

		List<String> segString = new ArrayList<String>();
		List<Integer> labelString = new ArrayList<Integer>();
		List<Document> listDoc = new ArrayList<Document>();
		List<Document> listDoc1 = new ArrayList<Document>();
		List<Document> listDoc2 = new ArrayList<Document>();
		
//		"ban-hang", "xay-dung", "kien-truc", "ngan-hang", "it-phan-mem", "giao-duc", "du-lich", "co-khi-che-tao", "ke-toan", "phap-luat-ly", "bao-hiem", "nha-hang-khach-san", "dien-tu-lanh", "nhan-su", "marketing", "it-phan-cung", "bat-dong-san", "y-te-duoc", "cong-nghe-cao", "dau-khi"
		
//		String[] field = {"xay-dung", "kien-truc", "ngan-hang", "it-phan-mem", "co-khi-che-tao", "dien-tu-lanh"};
		
		String[] field = {"co-khi-che-tao", "xay-dung"};
		for (int i = 0; i < field.length; i++) {
			listDoc2 = ReadDB.readFile("JOBDBTest", field[i]);
			listDoc1.addAll(listDoc2);
		}
			
		for (int i = 0; i < listDoc1.size(); i++) {
			String str = listDoc1.get(i).getContent();
			str = RemoveSB.removeSB(str);
			listDoc1.get(i).setContent(str);
		}
		
		for (int i = 0; i < listDoc1.size(); i++) {
			String str = listDoc1.get(i).getContent();
//			str = str.replaceAll("\\n","");
			str = segmenter.segmentRawString(str);
			boolean check = CheckVietNameseDocs.checkVnDoc(str);
			if (check == true) {
				Document doc = new Document(listDoc1.get(i).getLabel(),str);
				listDoc.add(doc);
			}
		}
		
		for (Document document : listDoc) {
			segString.add(document.getContent());
			labelString.add(document.getLabel());
		}
			
		List<String> data = new ArrayList<String>();
		for (String string : segString) {
			data.addAll(RemoveStopWord.removeStopWord(string));
		}
		System.out.println(segString.size());
		
		Map<String, Double> TokenIdfMap = ReadFile.readFile("src/preprocess/data/TokenIdf.txt");
		List<String> dict = new ArrayList<String>();
		List<Double> idf = new ArrayList<Double>();
		for (String str : TokenIdfMap.keySet()) {
			dict.add(str);
			idf.add(TokenIdfMap.get(str));
		}
		
		String allNode = "";
		
		for (int i = 0; i < segString.size(); i++) {
			String node = Vector.NodeCalculating(idf, dict, segString.get(i));
			allNode = allNode + labelString.get(i) + " " + node + "\n";	
		}
				
		WriteFile.writeFile("src/test/data/testdata.test", allNode);
		
		System.err.println("Done!");
	}
}
