package demoSVM;

import java.io.IOException;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;

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
		
		String str1 = "C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train";
		String str2 = "C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train.model2";
		
		svm_train.train(str1, str2);
		
		
		System.out.println(predict("5:1 7:1 14:1 19:1 39:1 40:1 51:1 63:1 67:1 73:1 74:1 76:1 78:1 83:1 \n"
				,"C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train.model"));
		
	}

}
