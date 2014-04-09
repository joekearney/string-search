package joe.strings.testfw;

import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Resources;

public final class SampleStrings {
	public static final String EMPTY_STRING = "";
	public static final String SINGLE_CHAR = "a";
	public static final String WORD = "play";
	public static final String SENTENCE = "This fellow is wise enough to play the fool;";

	public static final String TWELFTH_NIGHT = loadText("twelfth-night");
	public static final String SHAKESPEARE = loadText("shakespeare");

	private static final int PARAGRAPH_SIZE = 2000;
	public static final String PARAGRAPH_FROM_12N = TWELFTH_NIGHT.substring(TWELFTH_NIGHT.length() / 2, (TWELFTH_NIGHT.length() / 2) + PARAGRAPH_SIZE);
	public static final String PARAGRAPH_FROM_SHAKESPEARE;
	static {
		int sentenceLocation = SHAKESPEARE.indexOf(SENTENCE);
		PARAGRAPH_FROM_SHAKESPEARE = SHAKESPEARE.substring(sentenceLocation, sentenceLocation + PARAGRAPH_SIZE);
	}

	public static final String THOUSAND_A = Strings.repeat("a", 1000);
	public static final String HUNDRED_A_B = Strings.repeat("a", 100) + "b";

	private static String loadText(String name) {
		String text;
		try {
			URL url = Resources.getResource("joe/strings/" + name + ".txt");
			text = Resources.toString(url, Charsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load long text", e);
		}
		return text;
	}

	private SampleStrings() {}
}
