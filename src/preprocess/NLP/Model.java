package preprocess.NLP;



public class Model {
	
//	private int id;
	private String url;
	private String desc;
	private String req;
	
	public Model(String url, String desc, String req) {
		this.url = url;
		this.desc = desc;
		this.req = req;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	
	
}
