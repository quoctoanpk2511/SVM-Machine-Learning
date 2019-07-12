package crawler;

public class Main {

	public static void main(String[] args) {
		
		String nameFileList[]= {
//				"ban-hang",  //1 bo
//				"xay-dung",  
//				"kien-truc", //3 bo
//				"ngan-hang",
//				"it-phan-mem",//5
//				"giao-duc",
//				"du-lich",   //7
//				"co-khi-che-tao", 
//				"ke-toan",   //9 bo
//				"phap-luat-ly",
//				"bao-hiem",//11 **
//				"nha-hang-khach-san",
//				"dien-tu-lanh",//13 ******bo
//				"nhan-su",//**  // bo
//				"marketing",//15 **
//				"it-phan-cung",  //  bo
//				"bat-dong-san",//17 bo
//				"y-te-duoc",
//				"cong-nghe-cao",//19 bo
//				"dau-khi",   // bo
				"phien-dich",
//				"dich-vu"
				};
		
		//vieclam24h
		String urlList3[]= {
//				"ban-hang-c63.html",//1
//				"xay-dung-c41.html",
//				"kien-truc-tk-noi-that-c15.html",//3
//				"ngan-hang-c22.html",
//				"it-phan-mem-c74.html",//5
//				"giao-duc-dao-tao-c10.html",
//				"du-lich-c9.html",//7
//				"co-khi-che-tao-c4.html",
//				"ke-toan-kiem-toan-c30.html",//9
//				"phap-ly-c18.html",
//				"bao-hiem-c1.html",//11
//				"khach-san-nha-hang-c84.html",
//				"dien-dien-tu-c8.html",//13
//				"nhan-su-c59.html",
//				"marketing-pr-c53.html",//15
//				"it-phan-cung-mang-c5.html",
//				"kd-bat-dong-san-c81.html",//17
//				"y-te-duoc-c43.html",
//				"cong-nghe-cao-c88.html",//19
//				"dau-khi-hoa-chat-c6.html",
				"bien-phien-dich-c79.html"
				};
		
		//mywork
		String urlList2[]= {
//				"31",//1
//				"10",
//				"9",//3
//				"7",
//				"38",//5
//				"58",
//				"12",//7
//				"67",
//				"6",//9
//				"62",
//				"3",//11
//				"13",
//				"69",//13
//				"75",
//				"43",//15
//				"37",
//				"5",//17
//				"65",
//				"15",//19
//				"68",
				"56",
				"57"
				};
		
		String urlList[]= {
//				"viec-lam-ban-hang-i33-vn",//1
//				"viec-lam-xay-dung-i7-vn",
//				"viec-lam-kien-truc-thiet-ke-noi-that-i5-vn",//3
//				"viec-lam-ngan-hang-i42-vn",
//				"viec-lam-it-phan-mem-i35-vn",//5
//				"viec-lam-giao-duc-dao-tao-i12-vn",
//				"viec-lam-hang-khong-du-lich-khach-san-i37-vn",//7
//				"viec-lam-co-khi-i65-vn",
//				"viec-lam-ke-toan-tai-chinh-i1-vn",//9
//				"viec-lam-phap-ly-i25-vn",
//				"viec-lam-bao-hiem-i24-vn",//11
//				"viec-lam-nha-hang-khach-san-i73-vn",
//				"viec-lam-dien-dien-tu-i64-vn",//13
//				"viec-lam-nhan-su-i23-vn",
//				"viec-lam-marketing-i27-vn",//15
//				"viec-lam-it-phan-cung-mang-i55-vn",
//				"viec-lam-bat-dong-san-i30-vn",//17
//				"viec-lam-y-te-cham-soc-suc-khoe-i22-vn",
//				"viec-lam-cong-nghe-cao-i66-vn",//19
//				"viec-lam-dau-khi-i28-vn",
				};
		
		int size = nameFileList.length;

		Driver3[] d3 = new Driver3[20];
//		Driver2[] d2 = new Driver2[20];
		for(int i = 0;i<size;i++) {
				d3[i] = new Driver3();
				
				d3[i].setFileName(nameFileList[i]);
				d3[i].setUrl(urlList3[i]);
				d3[i].setLabel(i+1);
				d3[i].run();
				//vietnamwork
			
//			d2[i] = new Driver2();
//			
//			d2[i].setFileName(nameFileList[i]);
//			d2[i].setUrl(urlList2[i]);
////			d2[i].setLabel(i+1);
//			d2[i].run();
			
		}
	}

}
