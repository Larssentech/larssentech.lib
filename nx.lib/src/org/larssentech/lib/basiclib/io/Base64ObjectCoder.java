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

package org.larssentech.lib.basiclib.io;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

/**
 * Utility class. This class implements a number of convenience methods to
 * encode and decode binary to and from Base64 for text-based transmission of
 * data. For this reason, all the methods are static, there is no
 * instance-specific variable or functionality.
 * 
 * This class relies on the imported Apache Base64 replacement for the older
 * (and proprietary) Sun Microsystems Base64Encoder class
 * 
 * @author jcer
 *
 */

public class Base64ObjectCoder {

	public static byte[] decodeBytes(byte[] base64data) throws DecoderException {

		return new Base64().decode(base64data);
	}

	public static byte[] encodeBytes(byte[] binaryData) throws EncoderException {

		return new Base64().encode(binaryData);
	}

	/**
	 * This one chunks the data into neat rows of the same length
	 * 
	 * @param binaryData
	 * @return
	 * @throws EncoderException
	 */
	public static byte[] encodeBytesAndChunk(byte[] binaryData) throws EncoderException {

		return new Base64().encode(binaryData);
	}

}
