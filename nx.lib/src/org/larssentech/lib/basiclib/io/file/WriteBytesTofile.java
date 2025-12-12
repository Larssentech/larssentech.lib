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

package org.larssentech.lib.basiclib.io.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WriteBytesTofile {
	public WriteBytesTofile() {

	}

	public static boolean writeBytesToFile(byte[] inArr, String fileName) {

		try {

			WriteBytesTofile.writeBytesToFile(new ByteArrayInputStream(inArr), fileName);
			return true;
		}
		catch (Exception e) {

			System.out.println("WriteBytesToFile class: " + e);

			System.out.println("File: " + fileName + " exists: " + new File(fileName).exists() + " Length: " + new File(fileName).length());

			return false;
		}
	}

	private static long writeBytesToFile(InputStream in, String fileName) throws FileNotFoundException, IOException {

		FileOutputStream out = new FileOutputStream(fileName);

		int thisRead = 0;
		byte[] buf = new byte[1024];

		while ((thisRead = in.read(buf)) != -1) {

			out.write(buf, 0, thisRead);
			out.flush();
		}
		// Close all streams
		in.close();
		out.close();

		System.out.println("File: " + fileName + " exists: " + new File(fileName).exists() + " Length: " + new File(fileName).length());

		return new File(fileName).length();
	}
}
