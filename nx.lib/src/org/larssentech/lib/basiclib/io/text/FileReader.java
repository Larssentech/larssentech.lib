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

package org.larssentech.lib.basiclib.io.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {

	// (c) 2005-2011 AVANZ.IO

	public static String readCharsFromFile(String fileName) {

		FileInputStream in = null;

		try {

			in = new FileInputStream(fileName);
		} catch (IOException ioe) {

			System.out.println("Problem creating stream to file: " + fileName + " - " + ioe.toString());
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		// int thisRead = 0;
		char[] buf = new char[1024];
		String s = new String();

		try {

			while ((reader.read(buf)) != -1) { s += buf; }

			reader.close();
			in.close();

			return s.toString();

		} catch (IOException ioe) {

			System.out.println("Problem reading chars out of file: " + fileName + " - " + ioe.toString());
		}
		return "";
	}
}
