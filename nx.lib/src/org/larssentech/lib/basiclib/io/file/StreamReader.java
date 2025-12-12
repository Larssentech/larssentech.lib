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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author jcer This class is responsible for the reading of bytes from a binary
 *         file in a sequential manner. This class does not manage the
 *         iterations over the file length but is only concerned with the next
 *         binary packet.
 * 
 */

public class StreamReader {

	FileInputStream in;

	public FileInputStream getIn() {
		return in;
	}

	public StreamReader(File file) {

		try {

			this.in = new FileInputStream(file);
		} catch (FileNotFoundException e) {

			System.out.println("File " + file.getName() + " not found, why?");
			e.printStackTrace();
			this.in = null;
		}
	}

	// Someone else has to loop invoking this byte[] by byte[]
	public int readBytes(byte[] bytes) {

		try {

			return this.in.read(bytes);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public void closeStream() {

		try {

			this.in.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}