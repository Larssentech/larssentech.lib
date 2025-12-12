/*
 * Copyright 1999-2024 Larssentech Developers
 *
 * This file is part of the Larssentech BasicLib2 project.
 *
 * The Larssentech BasicLib2 Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * XKomm is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the source code.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.lib.basiclib.io.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineCounter {
	public LineCounter() {

	}

	public static int countLinesFrom(String fileName) {

		return LineCounter.countLinesFrom(new File(fileName));
	}

	private static int countLinesFrom(File aFile) {

		try {

			BufferedReader fromFile = new BufferedReader(new FileReader(aFile));
			int lines = 0;

			while (fromFile.readLine() != null) { lines++; }
			fromFile.close();
			return lines;
		} catch (IOException e) {

			return 0;
		}
	}

}
