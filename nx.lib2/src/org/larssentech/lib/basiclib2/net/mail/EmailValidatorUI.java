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

import org.larssentech.lib.log.Logg3r;

public class EmailValidatorUI {

	public static final String SP = "   ";
	public static final String LN = "-------------------------------" + "-------------------------------";
	public static final String V = "1.2.2.0";
	public static final String Y = "2001-2010";

	public static void main(String[] args) {

		Logg3r.log("");
		Logg3r.log(LN);
		Logg3r.log(SP + "Larssentech EmailValidator v" + V);
		Logg3r.log(SP + "Copyright (c) Larssentech SII Solutions " + Y);
		Logg3r.log(LN);

		if (args.length == 0) {

			Logg3r.log(SP + "Need some addresses to test. One at least");
			Logg3r.log(SP + "If more than one, they MUST be space separated.");
			Logg3r.log(SP + "Otherwise, unexpected results may occur");
			Logg3r.log(SP + "Usage: java EmailValidator address1 address2 ...");
			Logg3r.log(LN);
			Logg3r.log("");
			System.exit(-1);
		}

		// ArrayList bad = new ArrayList();
		try {

			Vector<String> bad = EmailValidator.validateEmail(args);
			Logg3r.log("");
			Logg3r.log(LN);
			if (bad.size() == 0) Logg3r.log(SP + "Done. All emails OK");
			else if (bad.size() > 0) {

				Logg3r.log(SP + "Problem with one or more email addresses.");
				Logg3r.log(SP + "Error(s):");
				Logg3r.log("");

				for (int i = 0; i < bad.size(); i++) Logg3r.log(SP + "- " + bad.get(i).toString());
			}
			Logg3r.log(LN);
			Logg3r.log("");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}