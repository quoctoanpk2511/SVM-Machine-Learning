package preprocess.trainning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import preprocess.NLP.RDRsegmenter;
import preprocess.trainning.Dict;

public class SVMTrainning {
	public static void main(String[] args) throws IOException {
		RDRsegmenter segmenter = new RDRsegmenter();

		List<String> segString = new ArrayList<String>();
		List<Integer> labelString = new ArrayList<Integer>();
		List<Document> listDoc = new ArrayList<Document>();
		List<Document> listDoc1 = new ArrayList<Document>();
		List<Document> listDoc2 = new ArrayList<Document>();
		
//		"ban-hang"1, , "ke-toan"9
		
//		String[] field = {"xay-dung", "kien-truc", "ngan-hang", "it-phan-mem", "giao-duc", "du-lich", "co-khi-che-tao", "phap-luat-ly", "bao-hiem", "nha-hang-khach-san", "dien-tu-lanh", "nhan-su", "marketing", "it-phan-cung", "bat-dong-san", "y-te-duoc", "cong-nghe-cao", "dau-khi"};
		String[] field = {"du-lich", "giao-duc"};
		for (int i = 0; i < field.length; i++) {
			listDoc2 = ReadDB.readFile("TestDB", field[i]);
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
		
		System.out.println(listDoc.size());

		
		for (Document document : listDoc) {
			segString.add(document.getContent());
			labelString.add(document.getLabel());
		}
			
		List<String> data = new ArrayList<String>();
		for (String string : segString) {
			data.addAll(RemoveStopWord.removeStopWord(string));
		}
		System.out.println(segString.size());
		
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
			allNode = allNode + labelString.get(i) + " " + node + "\n";	
		}
		
		
		WriteFile.writeFile("src/preprocess/data/traindata.train", allNode);
		
//		System.err.println("done");
	}
	
	
}

