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

package org.larssentech.lib.basiclib.io.file;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytesFromFile {

	public static byte[] readBytesFromFile(String fileName) {

		FileInputStream in = null;

		try {

			in = new FileInputStream(fileName);
		} catch (IOException ioe) {

			System.out.println("Problem creating stream to file: " + fileName + " - " + ioe.toString());
			return new byte[0];
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int thisRead = 0;
		byte[] buf = new byte[1024];

		try {

			while ((thisRead = in.read(buf)) != -1) { out.write(buf, 0, thisRead); }
			in.close();
		} catch (IOException ioe) {

			System.out.println("Problem reading bytes out of file: " + fileName + " - " + ioe.toString());
			return new byte[0];
		}
		return out.toByteArray();
	}

}
