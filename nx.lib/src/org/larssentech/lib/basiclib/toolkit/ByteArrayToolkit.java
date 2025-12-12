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

public class ByteArrayToolkit {

	public static byte[] shrinkArray(byte[] bytes, int i) {

		byte[] bytesShort = new byte[i];
		for (int x = 0; x < bytesShort.length; x++) bytesShort[x] = bytes[x];

		return bytesShort;
	}

	public static byte[] concatArrays(byte[] blockHeader, byte[] b64) {

		int newLength = blockHeader.length + b64.length;
		byte[] bytesLonger = new byte[newLength];

		int i = 0;

		for (i = 0; i < blockHeader.length; i++) { bytesLonger[i] = blockHeader[i]; }

		for (int j = 0; j < b64.length; j++) {

			bytesLonger[i] = b64[j];
			i++;
		}
		return bytesLonger;
	}
}
