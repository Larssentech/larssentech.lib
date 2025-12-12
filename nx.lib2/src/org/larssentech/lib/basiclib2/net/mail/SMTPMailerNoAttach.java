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

public class SMTPMailerNoAttach {

	public static boolean SMTPMailData(EmailEngine ee) {

		try {

			ee.frS();
			ee.toS("HELO " + ee.getFrom().substring(ee.getFrom().indexOf("@") + 1, ee.getFrom().length()));
			ee.frS();
			ee.toS("MAIL FROM:<" + ee.getFrom() + ">");
			ee.frS();
			ee.toS("RCPT TO:<" + ee.getTo() + ">");
			ee.frS();
			ee.toS("DATA");
			ee.frS();
			ee.toS("Date: " + new Date().toString());
			ee.toS("From: " + ee.getFrom());
			ee.toS("To: " + ee.getTo());
			ee.toS("Subject: " + ee.getSubject());
			ee.toS("MIME-Version: 1.0");
			ee.toS("Content-Type: text/html");

			for (int i = 0; i < ee.getBody().length; i++) { ee.toS(ee.getBody()[i]); }
			ee.toS(".");

			ee.frS();
			ee.toS("QUIT");
			ee.frS();
			// End of SMTP Communication
		} catch (Exception e) {

			System.out.println("Problem: ");
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
