package demoSVM;

import java.io.IOException;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import preprocess.trainning.ReadFile;

public class demoSVM {

	public demoSVM() {
		// TODO Auto-generated constructor stub
	}
		
	
	public static svm_node[] ConvertToNodes(String vector) {
		String[] nodeArr = vector.split("\\s");
		svm_node[] nodes = new svm_node[nodeArr.length];
		for (int i = 0; i < nodeArr.length; i++) {
			nodes[i] = new svm_node();
			String[] word = nodeArr[i].split(":");
			nodes[i].index = Integer.valueOf(word[0]);
			nodes[i].value = Double.valueOf(word[1]);
		}
		return nodes;
	}
	
	public static double predict(String vector,String model_file_path) throws IOException {
		String pathmodel=model_file_path;
		svm_model model = svm.svm_load_model(pathmodel);
		svm_node[] nodes = ConvertToNodes(vector);
		double label=svm.svm_predict(model,nodes);
		return label;
		
	}
	
	public static void main(String[] args) throws IOException {
		
//		String str1 = "C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train";
//		String str2 = "C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train.model2";
//		
//		svm_train.train(str1, str2);

		
		System.out.println(predict("93:0.08224 503:0.207 848:0.16483 1043:0.09425 1224:0.19197 1581:0.03924 1774:0.03924 1830:0.17244 2117:0.16755 2134:0.09425 2265:0.0695 2383:0.06399 2402:0.08224 2431:0.15122 2514:0.09672 2605:0.10516 2865:0.10516 2908:0.13899 3274:0.03924 3296:0.08224 3344:0.08564 3561:0.0695 3835:0.03924"
				,"C:\\Users\\QuocToan\\Desktop\\libsvm-3.23\\windows\\traindata.train.model"));
		
	}

}
