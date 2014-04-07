package joe.strings.testfw;

/**
 * A {@link CharSequence} that exposes its array. This is primarily for testing where you want to be able to compare to methods in {@link String} that have
 * access directly to the array without extra bounds checking and method indirection.
 *
 * @author Joe Kearney
 */
public final class CharSequenceExposingArray implements CharSequence {
	private final char[] array;

	private CharSequenceExposingArray(String string) {
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
	 * Gets the array
	 * @return
	 */
	public char[] toCharArray() {
		return array;
	}

	public static CharSequence forString(String string) {
		return new CharSequenceExposingArray(string);
	}

}
