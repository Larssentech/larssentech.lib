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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jcer This class is responsible for the writing of bytes to a binary
 * file in a sequential manner. This class does not manage the iterations over
 * the file length but is only concerned with the next binary packet.
 * 
 */

public class StreamWriter {

	private FileOutputStream out;

	public StreamWriter(File file) {

		try {
			this.out = new FileOutputStream(file.getAbsolutePath(), true);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Someone else has to loop invoking this byte[] by byte[]
	public double writeBytes(byte[] bytes, int i) {

		try {

			double bytesSaved = bytes.length;
			this.out.write(bytes, 0, i);
			return bytesSaved;

		}
		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		catch (IOException e) {

			e.printStackTrace();
		}

		return 0;
	}

	public void closeStream() {

		try {

			this.out.close();
		}
		catch (IOException e) {

			e.printStackTrace();
		}
	}
}