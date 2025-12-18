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

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SaveToFile {
	public SaveToFile() {

	}

	public static boolean saveToFile(String fileName, String dataLine, boolean append) {

		return saveToFile(fileName, new String[] { dataLine }, append);
	}

	public static boolean saveToFile(File fileName, String dataLine, boolean append) {

		return saveToFile(fileName, new String[] { dataLine }, append);
	}

	private static boolean saveToFile(File file, String[] strings, boolean append) {

		return saveToFile(file.getAbsoluteFile().toString(), strings, append);
	}

	public static boolean saveToFile(String fileName, String[] dataLine, boolean append) {

		try {

			PrintWriter toFile = new PrintWriter(new FileWriter(fileName, append));

			for (int i = 0; i < dataLine.length; i++) toFile.println(dataLine[i]);

			toFile.close();

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public static boolean saveToFile(boolean deleteFirst, String fileName, String[][] dataArray, boolean append) {

		try {

			if (deleteFirst) new File(fileName).delete();
			PrintWriter toFile = new PrintWriter(new FileWriter(fileName, append));

			for (int i = 0; i < dataArray.length; i++) {

				for (int j = 0; j < dataArray[i].length; j++) {

					toFile.print(dataArray[i][j]);

					if (j < dataArray[i].length - 1) toFile.print("\t");

				}

				if (i < dataArray.length - 1) toFile.println();

			}
			toFile.close();

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

}
