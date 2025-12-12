/*
 * Copyright 1999-2024 Larssentech Developers
 *
 * This file is part of the Larssentech BasicLib2 project.
 *
 * The Larssentech BasicLib2 Library is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the source code. If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.lib.basiclib.toolkit;

import java.util.Vector;

public class StringManipulationToolkit {
	private static final String QUOTE = "\"";
	public static final String TAB = "\t";

	private static Vector chopStringIntoChunks(String longString0, int chunkSize) {

		String longString = longString0;
		Vector messageList = new Vector();
		// Now break the message into 200byte chunks and clear from the
		// beginning of the message the chars we have put in the List

		while (longString.length() > chunkSize) {

			messageList.addElement(longString.substring(0, chunkSize));
			longString = longString.substring(chunkSize, longString.length());
		}
		// for the last bit
		messageList.addElement(longString);

		return messageList;
	}

	public static String insertThousandSeparator(String numberToFormat) {

		String formattedNumber = "";

		int counter = 0;

		for (int i = numberToFormat.length() - 1; i >= 0; i--) {

			counter++;

			formattedNumber += numberToFormat.charAt(i);

			new StringManipulationToolkit();

			if (counter % 3 == 0 && i > 0 && counter < StringManipulationToolkit.replaceAll(numberToFormat, "-", "").length()) {

				formattedNumber += ",";

			}

		}

		return StringManipulationToolkit.reverseString(formattedNumber);

	}

	private static String reverseString(String string) {

		String reversedString = "";

		for (int i = string.length() - 1; i >= 0; i--) {

			reversedString += string.charAt(i);

		}

		return reversedString;

	}

	public static String[] chunkString(String longString, int chunkSize) {

		Vector v = StringManipulationToolkit.chopStringIntoChunks(longString, chunkSize);
		String[] s = new String[v.size()];
		for (int i = 0; i < v.size(); i++) s[i] = v.elementAt(i).toString();
		return s;
	}

	public static String replaceAll(String originalString, String stringToReplace, String substituteString) {

		if (originalString != null && stringToReplace != null && substituteString != null) {

			String tempString = "";

			int start = 0;

			int charCount = 0;

			if (originalString.indexOf(stringToReplace) != -1) {

				while ((charCount = originalString.indexOf(stringToReplace, start)) != -1) {

					tempString += originalString.substring(start, charCount);

					tempString += substituteString;

					start = charCount + stringToReplace.length();

				}

				tempString += originalString.substring(start, originalString.length());

				return tempString;

			}

			else {

				return originalString;

			}

		}

		else {

			return "";

		}

	}

	public static String HTMLEncodeString(String s) {

		String out = "";

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if (c > 127) {

				out += "&#";
				out += ((int) c);

			} else out += c;
		}
		return out.toString();
	}

	public static String HTMLDecodeString(String s0) {

		final String key = "&#";
		final int h = 5;
		int j = -1;

		String s = s0;

		while ((j = s.indexOf(key)) != -1) {

			// Save piece before the &#
			String out = s.substring(0, j);

			// Get the encoded part
			String my5 = s.substring(j + key.length(), j + h);

			// Turn it into plain text
			char c1 = (char) Integer.parseInt(my5);

			// Save it
			out += c1;

			// Save it all back plus the rest of the string
			// as the original string to go back into the loop
			s = out += s.substring(j + 5, s.length());

		}
		return s;
	}

	public static String unQuote(String myString) {

		if (myString.startsWith("\"")) myString = myString.substring(1, myString.length());
		if (myString.endsWith("\"")) myString = myString.substring(0, myString.length() - 1);

		return myString;
	}

	public static String quoter(String value) {

		return QUOTE + value + QUOTE;
	}
	
	public static String tsvEncodeRequest(String[] array) {

		String tsvString = "";

		for (int i = 0; i < array.length - 1; i++) {

			tsvString += array[i] + "\t";

		}

		tsvString += array[array.length - 1];

		return tsvString;

	}

	public static boolean validEmail(String email) {

		return email.indexOf("@") > 0 && //$NON-NLS-1$
				email.indexOf(".") > 0; //$NON-NLS-1$
	}

	public static String roundFloat(float f, int d) {

		String fS = "" + f;

		if (fS.indexOf(".") > 0 && fS.substring(fS.indexOf(".")).length() > d) return fS.substring(0, fS.indexOf(".") + (d + 1));

		return fS;
	}

	public static String eyeLiner(int len) {

		String returnS = "";
		for (int i = 0; i < len; i++) returnS += "-";
		return returnS;

	}

	public static String padBothSides(String string2Pad, String pad) {

		return pad + string2Pad + pad;
	}

	public static String padNumber(long number, String pad) {

		if (number < 10) return pad + pad + pad + pad + number;
		else if (number < 100) return pad + pad + pad + number;
		else if (number < 1000) return pad + pad + number;
		else if (number < 10000) return pad + number;
		else return "" + number;
	}

	public static String flattenStringArray(String[] dir, String token) {

		String returnString = "";

		for (int i = 0; i < dir.length; i++) {
			returnString += dir[i] + token;
		}

		return returnString;

	}
}
