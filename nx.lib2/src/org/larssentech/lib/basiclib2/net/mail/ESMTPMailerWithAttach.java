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

import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

class ESMTPMailerWithAttach {
	static boolean ESMTPmailDataWithAttachment(EmailEngine ee) throws IOException, EncoderException {
		// ee.toC("Mail server implements ESMTP");

		ee.toS("EHLO " + ee.getFrom().substring(ee.getFrom().indexOf("@") + 1, ee.getFrom().length()));

		// Read as many lines as sent
		while (!ee.frS().equals("250 OK")) {}
		;

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

		if (ee.getCc().length() > 5) ee.toS("RCPT TO:<" + ee.getCc() + ">");

		ee.frS();

		ee.toS("DATA");
		ee.frS();
		ee.toS("Date: " + new Date().toString());
		ee.toS("From: " + ee.getFrom());
		ee.toS("To: " + ee.getTo());
		ee.toS("cc: " + ee.getCc());
		ee.toS("Subject: " + ee.getSubject());

		ee.toS("MIME-Version: 1.0");
		ee.toS("Content-Type: multipart/mixed; boundary=<nx_boundary>");
		ee.toS("");

		ee.toS("--<nx_boundary>");
		ee.toS("Content-Type: text/plain");
		ee.toS("");
		ee.toS(ee.getOneLineBody());
		ee.toS("");
		ee.toS("-- Sent using Larssentech Secure Email Engine");
		ee.toS("");

		for (int i = 0; i < ee.getAttachmentName().length; i++) {
			if (ee.getAttachmentName()[i].length() > 0 && ee.getAttachment64()[i].length() > 0) {

				EmailEngine.toC("Sending attachment. Size: " + ee.getAttachment64()[i].length() + " bytes");

				ee.toS("--<nx_boundary>");
				ee.toS("Content-Type: application/octet-stream");
				ee.toS("Content-Transfer-Encoding: base64");
				ee.toS("Content-Disposition: attachment; filename= \"" + ee.getAttachmentName()[i] + "\"");
				ee.toS("");

				ee.toS(ee.getAttachment64()[i]);
				EmailEngine.toC("Attachment " + ee.getAttachmentName()[i] + " sent.");
			}
		}

		ee.toS("");

		ee.toS("--<nx_boundary>--");
		ee.toS(".");

		ee.frS();
		ee.toS("QUIT");

		ee.frS();

		return true;
	}
}
