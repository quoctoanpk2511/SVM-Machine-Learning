package crawler;

public class Main {

	public static void main(String[] args) {
		

		
		String nameFileList[]= {
				"ban-hang",
				"xay-dung",
				"kien-truc",
				"ngan-hang",
				"it",
				"giao-duc",
				"du-lich",
				"co-khi-che-tao"
				};
		
		//vieclam24h
		String urlList1[]= {
				"ban-hang-c63.html",
				"xay-dung-c41.html",
				"kien-truc-tk-noi-that-c15.html",
				"ngan-hang-c22.html",
				"it-phan-mem-c74.html",
				"giao-duc-dao-tao-c10.html",
				"du-lich-c9.html",
				"co-khi-che-tao-c4.html"
				};
		
		//mywork
		String urlList2[]= {
				"31",
				"10",
				"9",
				"7",
				"38",
				"58",
				"12",
				"67"
				};
		int size = urlList1.length;
		Driver2 d = new Driver2();
		for(int i = 0;i<size;i++) {
			d.setFileName(nameFileList[i]);
			d.setUrl(urlList2[i]);
			d.run();
		}

		

		
		
	}

}
