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


import java.util.Date;

import org.apache.commons.codec.binary.Base64;

class ESMTPMailerNoAttach {

	boolean sent = false;

	boolean ESMTPMailData(EmailEngine ee) {
		this.sent = false;
		try {
			EmailEngine.toC("Mail server implements ESMTP");
			// ESMTP Communication
			System.out.println(ee.getFrom());
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
			ee.toS("Date: " + new Date());
			ee.toS("From: " + ee.getFrom());
			ee.toS("To: " + ee.getTo());
			ee.toS("Subject: " + ee.getSubject());
			ee.toS("MIME-Version: 1.0");
			ee.toS("Content-Type: text/plain");
			ee.toS(""); // Empty line after content descriptor header

			for (int i = 0; i < ee.getBody().length; i++) { ee.toS(ee.getBody()[i]); }
			ee.toS(".");

			ee.frS();
			ee.toS("QUIT");

			ee.frS();
			// End of SMTP Communication
			this.sent = true;

			System.out.println("Sent: " + this.sent);
		}

		catch (Exception e) {
			System.out.println("Error. Mail Engine failure. Email was not sent. Check ESMTP settings.\n" + e);
			e.printStackTrace();
			System.out.flush();
			this.sent = false;
		}
		return this.sent;
	}
}