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
		
//		String str1 = "C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train";
//		String str2 = "C:\\Users\\DELL\\Desktop\\libsvm-master\\windows\\a1a.train.model2";
//		
//		svm_train.train(str1, str2);

		String string = Classification.Classifier("1. Tìm kiếm nhà thầu đủ năng lực phục vụ cho các dự án của tập đoàn. Thu thập thông tin gói thầu, lập hồ sơ mời thầu 3. Tiến hành mời thầu, tiếp nhận hồ sơ mời thầu, phân tích, đánh giá hồ sơ dự thầu của các nhà, lập đề xuất lựa chọn nhà thầu 4. Soạn thảo hợp đồng, phụ lục hợp đồng, thương thảo, đàm phán ký kết hợp đồng 5. Theo dõi việc thực hiện hợp đồng, xử lý các phát sinh sau khi ký kết hợp đồng 6. Phối hợp tốt với các bộ phận khác trong công ty để hoàn thành công tác đầu thầu 7. Thực hiện các công việc khác do trưởng bộ phận giao");
		System.out.println(predict(string, "C:\\Users\\QuocToan\\Desktop\\libsvm-3.23\\windows\\4jobold.train.model"));
		
	}

}
