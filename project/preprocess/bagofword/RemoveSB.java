package preprocess.bagofword;

import java.io.IOException;

public class RemoveSB {
public static String removeSB(String string) throws IOException {
		
			string = string.replaceAll("\\d", "");
			string = string.replaceAll("https://www", "");
			string = string.replaceAll("\\p{P}", "");
			string = string.replace('~', ' ');
			string = string.replace('`', ' ');
			string = string.replace('!', ' ');
			string = string.replace('@', ' ');
			string = string.replace('#', ' ');
			string = string.replace('$', ' ');
			string = string.replace('%', ' ');
			string = string.replace('^', ' ');
			string = string.replace('&', ' ');
			string = string.replace('_', ' ');
			string = string.replace('=', ' ');
			string = string.replace('/', ' ');
			string = string.replace('+', ' ');
			string = string.replace('-', ' ');
			string = string.replace('*', ' ');
			string = string.replace('/', ' ');
			string = string.replace('|', ' ');
			string = string.replace('\\', ' ');
			string = string.replace(':', ' ');
			string = string.replace(';', ' ');
			string = string.replace('"', ' ');
			string = string.replace('\'', ' ');
			string = string.replace('<', ' ');
			string = string.replace('>', ' ');
			string = string.replace(',', ' ');
			string = string.replace('.', ' ');
			string = string.replace('?', ' ');
			string = string.replace('●', ' ');
			string = string.replace('•', ' ');
			string = string.replace('➡', ' ');
			string = string.replace('', ' ');
			string = string.replace('…', ' ');
			string = string.replace('✓', ' ');
			string = string.replace('–', ' ');
			string = string.replace("✌️", "");
			string = string.replace("✦", "");
			string = string.replace("👑", "");

		return string;
	}
}
