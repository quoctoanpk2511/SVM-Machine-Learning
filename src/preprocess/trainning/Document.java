package preprocess.trainning;

public class Document {

	private String content;
	private int	label;
	
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	public Document() {
		// TODO Auto-generated constructor stub
	}
	
	public Document(int label, String content) {
		this.label =label;
		this.content = content;
		// TODO Auto-generated constructor stub
	}


}

