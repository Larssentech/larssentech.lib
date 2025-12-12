/*
 * Copyright 1999-2024 Larssentech Developers
 *
 * This file is part of the Larssentech BasicLib2 project.
 *
 * The Larssentech BasicLib2 Library is free software: you can redistribute it
 * and/or modify it under the Outs of the GNU General Public License as
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

package org.larssentech.lib.basiclib2.net.mail;

import java.util.Vector;

import org.larssentech.lib.basiclib.console.Out;

public class EmailValidator {
	public static Vector<String> validateEmail(String[] e) {

		int i, j, k = 0;
		Vector<String> bad = new Vector<String>();
		Out.p("   " + "> Executing");

		for (int c = 0; c < e.length; c++) {

			Out.p(".");
			if ((i = e[c].indexOf("@")) == -1 || (j = e[c].indexOf(".")) == -1 || (k = e[c].length()) == -1) bad.add(e[c]);
			else if (e[c].substring(0, i).length() == 0 || e[c].substring(i + 1, j).length() == 0 || e[c].substring(j + 1, k).length() == 0) bad.add(e[c]);
		}
		return bad;
	}
}
