package preprocess.trainning;

import java.io.IOException;

public class RemoveSB {
	// Loại bỏ kí tự đặc biệt
	public static String removeSB(String string) throws IOException {
		
		string = string.replaceAll("\\d", " ");
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
		string = string.replace("▪", "");
		string = string.replace("", "");
		string = string.replace("", "");
		string = string.replace("", "");
		string = string.replace("", "");
		string = string.replace("►", "");
		string = string.replace("₋", "");
		string = string.replace("☘", "");
		string = string.replace("♦", "");
		string = string.replace("⛔", "");
		string = string.replace("✒️", "");
		string = string.replace("👉", "");
		string = string.replace("👍", "");
		string = string.replace("💌", "");
		string = string.replace("🔸", "");
		string = string.replace("", "");
		string = string.replace("", "");
		string = string.replace("", "");
		string = string.replace("💰", "");
		string = string.replace("🏳️", "");
		string = string.replace("✔️", "");
		string = string.replace("✔", "");
		string = string.replace("✅", "");
		string = string.replace("→", "");
		string = string.replace("√", "");
		string = string.replace("🎄", "");
		string = string.replace("🎯", "");
		string = string.replace("📪", "");
		string = string.replace("−", "");
		string = string.replace("⛳", "");
		string = string.replace("➢", "");
		string = string.replace("📣", "");
		string = string.replace("☎", "");
		string = string.replace("❤", "");
		string = string.replace("\\n", "");
		string = string.replace("  ", "");
		string = string.replace("🌈", "");

	return string;
}
}
