package preprocess.trainning;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vector {
	
	public static List<Double> TfCalculating(List<String> dict, String segStr) throws IOException {
		List<String> data = new ArrayList<String>();
		for (String str : segStr.split(" ")) {
			data.addAll(RemoveStopWord.removeStopWord(str));
		}
		
		//vector
		List<Double> frequency = new ArrayList<Double>();
		for (int i = 0; i < dict.size(); i++) {
			double k = 0;
			for (int j = 0; j < data.size(); j++) {
				if (dict.get(i).equals(data.get(j))) {
					k = k +1;
				}
			}
			frequency.add(k);
		}	
        
        //Tinh tf
        List<Double> tf = new ArrayList<Double>();        
        for (int i = 0; i < frequency.size(); i++) {
			tf.add(frequency.get(i)/data.size());
		}
//        System.err.println(tf);

        return tf;

	}
	
	//Tinh tf-idf
	public static String NodeCalculating(List<Double> idf, List<String> dict, String segStr) throws IOException {
		
		List<Double> tf = new ArrayList<Double>();
		tf = TfCalculating(dict, segStr);
		
        List<Double> tf_idf = new ArrayList<Double>();
        for (int i = 0; i < dict.size(); i++) {
        	Double rate = tf.get(i)*idf.get(i);
        	tf_idf.add((double) Math.round(rate * 100000) / 100000);
		}
        
        String node = "";
        for (int i = 0; i < dict.size(); i++) {
        	if (tf_idf.get(i) != 0) {
        		node = node + (i + 1) + ":" + tf_idf.get(i) + " ";
			}			
		}
//        System.out.println(node);
        return node;
	}
	
	//Tinh idf
	public static List<Double> IdfCalculating(List<String> segString, List<String> dict) {

        List<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < dict.size(); i++) {
            int k = 0;
			for (int j = 0; j < segString.size(); j++) {
				a : for (String segStr : segString.get(j).split(" ")) {
					if (segStr.equals(dict.get(i))) {
						k = k + 1;
						break a;
					}
				}
			}
			if (k == 0) {
				count.add(1);
			}else {
				count.add(k);
			}
		} 
        List<Double> idf = new ArrayList<Double>();        
        for (int i = 0; i < dict.size(); i++) {
			idf.add(Math.log(segString.size()/count.get(i)));
//	        System.out.println(segString.size() + "////////"  + count.get(i));
		}

//        System.out.println(idf);
		return idf;
		
	}
}
