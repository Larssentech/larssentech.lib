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

package org.larssentech.lib.basiclib.io.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

import org.larssentech.lib.basiclib.io.file.ValidateFile;

public class ReadFromFile {
	public static String[] readFromFile(String fileName) {

		try {

			new ValidateFile();

			if (ValidateFile.validateFile(fileName)) {

				BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
				Stack stack = new Stack();
				int lineNumber = 0;
				String line;

				while ((line = fromFile.readLine()) != null) {

					stack.push(line);
					lineNumber++;
				}
				fromFile.close();
				String[] lineInFile = new String[lineNumber];
				lineNumber--; // To end backwards in index 0

				while (lineNumber >= 0) {

					lineInFile[lineNumber] = stack.pop().toString();
					lineNumber--;
				}

				if (lineInFile.length == 0) {
					lineInFile[0] = "";
				}
				return lineInFile;
			} else {

				String[] error = { "Error" };
				return error;
			}
		} catch (Exception e) {

			String[] except = { "Error: " + e.toString() };
			return except;
		}
	}

	public static String[] readFromFile2(String fileName) {

		try {

			new ValidateFile();

			if (ValidateFile.validateFile(fileName)) {

				BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
				Stack stack = new Stack();
				int lineNumber = 0;
				String line;

				while ((line = fromFile.readLine()) != null) {

					// Character encoding bug
					// line = new String(line.getBytes(), "UTF-8");

					stack.push(line);
					lineNumber++;
				}
				fromFile.close();
				String[] lineInFile = new String[lineNumber];
				lineNumber--; // To end backwards in index 0

				while (lineNumber >= 0) {

					lineInFile[lineNumber] = stack.pop().toString();
					lineNumber--;
				}

				if (lineInFile.length == 0) {
					lineInFile[0] = "";
				}
				return lineInFile;
			} else
				return new String[0];

		} catch (Exception e) {

			return new String[0];
		}
	}

	public static String[] readFromFile(String fileName, String stopAt) {
		try {

			new ValidateFile();

			if (ValidateFile.validateFile(fileName)) {

				BufferedReader fromFile = new BufferedReader(new FileReader(fileName));
				Stack stack = new Stack();
				int lineNumber = 0;
				String line;

				while ((line = fromFile.readLine()) != null) {

					if (line.startsWith(stopAt)) break;
					stack.push(line);
					lineNumber++;
				}
				fromFile.close();
				String[] lineInFile = new String[lineNumber];
				lineNumber--; // To end backwards in index 0

				while (lineNumber >= 0) {

					lineInFile[lineNumber] = stack.pop().toString();
					lineNumber--;
				}

				if (lineInFile.length == 0) {
					lineInFile[0] = "";
				}
				return lineInFile;
			} else {

				String[] error = { "Error" };
				return error;
			}
		} catch (Exception e) {

			String[] except = { "Error: " + e.toString() };
			return except;
		}
	}

}
