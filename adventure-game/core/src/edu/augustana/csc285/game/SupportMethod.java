package edu.augustana.csc285.game;

public class SupportMethod {
	/**
	 * 
	 * @param str
	 *            the string to wrap
	 * @param maxCharacter,
	 *            the number of max characters on a line assuming mono font
	 * @return a string with correct wrapping
	 */
	public static String wrapString(String str, int maxCharacter) {
		if (maxCharacter <= 0 || str == null || str == "") {
			throw new IllegalArgumentException("string is null or empty or maxCharacter is negative");
		} else {
			String wrappedVersion = "";
			String [] wordList = str.split(" ");
			String wrappedLine = wordList[0];
			for (int i = 1; i<wordList.length;i++) {
				String temp = wordList[i];
				if (temp.length() > maxCharacter) {
					throw new IllegalArgumentException("Number of max character is too small");
				} else {
					// if the wrappedLine would be too long then start a new
					// line
					if (wrappedLine.length() + 1 + temp.length() > maxCharacter) {
						wrappedVersion += wrappedLine + "\n";
						wrappedLine = temp;
					} else {
						wrappedLine += " " + temp;
					}
				}
			}
			wrappedVersion += wrappedLine;
			return wrappedVersion;
		}
	}
	
	public static void main (String [] args) {
		String str = "123 4567 89 123 4567 89";
		String wrappedStr = SupportMethod.wrapString(str, 7);
		System.out.println(wrappedStr);
	}

}
