package joe.strings.testfw;

/**
 * A {@link CharSequence} that exposes its array. This is primarily for testing where you want to be able to compare to methods in {@link String} that have
 * access directly to the array without extra copying, bounds checking and method indirection. Clearly the array is vulerable to being edited maliciously, but
 * this is not considered a problem if used only in tests so we assume that everyong plays nice together.
 *
 * @author Joe Kearney
 */
public final class CharSequenceExposingArray implements CharSequence {
	private final char[] array;
	private final String string;

	private CharSequenceExposingArray(String string) {
		this.string = string;
		this.array = string.toCharArray();
	}

	@Override
	public int length() {
		return array.length;
	}
	@Override
	public char charAt(int index) {
		return array[index];
	}
	@Override
	public CharSequence subSequence(int start, int end) {
		return CharSequenceExposingArray.forString(new String(array, start, end - start));
	}

	/**
	 * Gets the array. This is expected not to be modified; behaviour is undefined if it is.
	 *
	 * @return the array
	 */
	public char[] toCharArray() {
		return array;
	}
	@Override
	public String toString() {
		return string;
	}

	public static char[] toCharArray(CharSequence haystack) {
		final char[] array;
		if (haystack instanceof CharSequenceExposingArray) {
			array = ((CharSequenceExposingArray) haystack).toCharArray();
		} else if (haystack instanceof String) {
			array = ((String) haystack).toCharArray();
		} else {
			array = haystack.toString().toCharArray();
		}
		return array;
	}

	public static CharSequence forString(String string) {
		return new CharSequenceExposingArray(string);
	}

}
