package preprocess.bagofword;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteFile {
    public static void writeFile(String outFilePath, String allNode) throws IOException {
    	try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(outFilePath))) {
       	 	bw.write(allNode);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	 
	}
}
