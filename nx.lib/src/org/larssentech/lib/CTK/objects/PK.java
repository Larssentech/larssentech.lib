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

package org.larssentech.lib.CTK.objects;

import org.apache.commons.codec.DecoderException;
import org.larssentech.lib.basiclib.io.Base64ObjectCoder;

class PK {

	private final String base64String;
	private boolean good;

	public PK() {

		this.base64String = "NOPUK";
	}

	PK(String base64String) {

		this.base64String = base64String;
		if (this.base64String.length() > 50) this.setGood(true);
	}

	public String getStringValue() { return this.base64String; }

	public byte[] getByteArray() throws DecoderException { return Base64ObjectCoder.decodeBytes(this.base64String.getBytes()); }

	public boolean isGood() { return this.good; }

	private void setGood(boolean good) { this.good = good; }

}
