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
	public static void main(String[] args) throws IOException {
		
		RDRsegmenter segmenter = new RDRsegmenter();
		String string = "Được dịch từ tiếng Anh-LIBSVM và LIBLINEAR là hai thư viện máy học mã nguồn mở phổ biến, cả hai đều được phát triển tại Đại học Quốc gia Đài Loan và cả hai đều được viết bằng C ++ mặc dù có API C. LIBSVM thực hiện thuật toán tối ưu hóa tuần tự cho các máy vectơ hỗ trợ đã nhân, hỗ trợ phân loại và hồi quy.";
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
		System.out.println(node);
		WriteFile.writeFile("src/demoSVM/predict.txt", node);
		
	}
}
