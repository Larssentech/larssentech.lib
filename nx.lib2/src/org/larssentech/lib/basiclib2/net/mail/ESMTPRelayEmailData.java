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

package org.larssentech.lib.basiclib2.net.mail;

import org.apache.commons.codec.binary.Base64;

class ESMTPRelayEmailData {

	static boolean ESMTPrelayData(EmailEngine ee) {

		boolean sent = false;

		try {

			// ee.toC("Mail server implements ESMTP");
			// ESMTP Communication
			// System.out.println(ee.getFrom());
			ee.toS("EHLO " + ee.getFrom().substring(ee.getFrom().indexOf("@") + 1, ee.getFrom().length())); // Read
																											// as
																											// many
																											// lines
																											// as
																											// sent

			while (ee.frS().substring(3, 4).equals("-")) {}
			ee.toS("AUTH LOGIN");
			ee.frS();

			ee.toS(new Base64().encode(ee.getLogin().getBytes()).toString());
			ee.frS();

			ee.toS(new Base64().encode(ee.getPassword().getBytes()).toString());
			ee.frS();

			ee.toS("MAIL FROM:<" + ee.getFrom() + ">");
			ee.frS();

			ee.toS("RCPT TO:<" + ee.getTo() + ">");
			ee.frS();

			ee.toS("DATA");
			ee.frS();

			// Last line in body will be "."
			// Break each line into reasonable chunks because otherwise this
			// takes forever

			// for (int i = 0; i < ee.getBody().length; i++) {

			// String thisLine = ee.getBody()[i].trim() + "\r\n";

			// System.out.println(thisLine);
			// ee.toS(thisLine, true); // + "\r\n");

			ee.toS(ee.getBody()[0]);
			// }

			// for (int i = 0; i < ee.getBody().length; i++) {
			// StringTokenizer sto = new StringTokenizer(ee.getBody()[i],
			// "\r\n");
			// while (sto.hasMoreElements()) {
			// String thisLine = sto.nextElement().toString();
			// if (thisLine.length() == 0)
			// thisLine = "\r\n\r\n";
			// System.out.println(thisLine);
			// ee.toS(thisLine, true); // + "\r\n");
			// }
			// // ee.toS(ee.getBody()[i]);
			// }

			// ee.toS("\r\n");

			// ee.toSLn(".", false);

			ee.frS();
			ee.toS("QUIT");

			ee.frS();
			// End of SMTP Communication
			sent = true;

			System.out.println("Sent: " + sent);
		}

		catch (Exception e) {

			System.out.println("Error. Mail Engine failure. Email was not sent. Check ESMTP settings.\n" + e);
			e.printStackTrace();
			System.out.flush();
			sent = false;
		}
		return sent;
	}

}
