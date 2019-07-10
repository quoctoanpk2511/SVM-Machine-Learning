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
		String string = "- Tham gia lập kế hoạch và triển khai các hoạt động Marketing ngắn hạn và dài hạn.\r\n" + 
				"- Tham gia xây dựng, phát triển và quản trị nội dung trên các phương tiện truyền thông có chọn lọc\r\n" + 
				"- Tổ chức hoạt động, sự kiện liên quan đến hoạt động PR – Marketing\r\n" + 
				"- Thực hiện các nhiệm vụ khác theo yêu cầu của Trưởng phòng Marketing và Ban Giám Đốc";
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
