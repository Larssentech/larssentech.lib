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

package org.larssentech.lib.basiclib.io.parser;

import java.util.Stack;

import org.larssentech.lib.basiclib.toolkit.StringManipulationToolkit;

public class CSVExtractor2 {

	public static void main(String[] args) {

		new CSVExtractor2();
		CSVExtractor2.extractValuesFrom("\"String\",String,\"St,ring\",String");
	}

	public CSVExtractor2() {

	}

	public static String[] extractValuesFrom(String line0) {

		new StringManipulationToolkit();
		String line = StringManipulationToolkit.replaceAll(line0, "\"\"", "'");
		String[] fieldName;

		try {

			Stack stack = new Stack();
			int counter = 0, end = 0, start = 0;

			while (end < line.length()) {

				if (line.indexOf("\"", start) == start) {

					end = line.indexOf("\"", start + 1) + 1; // After the
																// closing "
																// which is
																// either before
																// a , or EO
																// line
					stack.addElement(line.substring(start + 1, end - 1));
				}
				else {

					// EO line...
					if ((end = line.indexOf(",", start)) == -1) {

						stack.addElement(line.substring(start, line.length()));
						counter++;
						break;
					}
					stack.addElement(line.substring(start, end));
				}
				start = end + 1;
				counter++;
			}
			fieldName = new String[counter];
			counter = counter - 1; // To be able to reach String[0]

			while (counter >= 0) {

				fieldName[counter] = stack.pop().toString();
				counter--;
			}
			return fieldName;
		}
		catch (Exception e) {

			fieldName = new String[1];
			fieldName[0] = "Error";
			System.out.println(e);
			return fieldName;
		}
	}
}
