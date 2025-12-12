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

package org.larssentech.lib.basiclib2.net.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.codec.EncoderException;

class EmailEngine {

	private boolean sent = false;
	private Socket SMTPSock;
	private PrintWriter toMailServer;
	private BufferedReader fromMailServer;
	private String smtp, login, plainPass, from, to, cc, subject, attPath;
	private String[] body;
	private String[] attachmentName;
	private String[] attachment64;

	private String oneLineBody;

	boolean sendESMTPMailWithAttachment(String SMTP, String login, String plainPass, String from, String to, String cc, String subject, String body, String[] attachmentName, String[] attachmentBase64)
			throws IOException, EncoderException {

		this.smtp = SMTP;
		this.login = login;
		this.plainPass = plainPass;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.oneLineBody = body;

		this.attachmentName = attachmentName;
		this.attachment64 = attachmentBase64;

		EmailEngine.toC("Running Class 'EmailDataWithAttachment'...");

		EmailEngine.toC("Creating UNIX Socket...");
		this.SMTPSock = new Socket(SMTP, 25);
		EmailEngine.toC("Done");

		EmailEngine.toC("Creating Input Network Stream...");
		this.fromMailServer = new BufferedReader(new InputStreamReader(this.SMTPSock.getInputStream()));
		EmailEngine.toC("Done");

		EmailEngine.toC("Creating Output Network Stream...");
		this.toMailServer = new PrintWriter(new OutputStreamWriter(this.SMTPSock.getOutputStream()));
		EmailEngine.toC("Done");

		EmailEngine.toC("");
		EmailEngine.toC(this.SMTPSock.toString());

		String serverLine;
		serverLine = this.fromMailServer.readLine();
		EmailEngine.fromServer(serverLine);

		if (serverLine.indexOf("ESMTP") != -1) this.sent = this.ESMTPmailDataWithAttachment();

		else this.sent = this.SMTPEmailWithAttachment();

		return this.sent;
	}

	private boolean SMTPEmailWithAttachment() {

		new SMTPMailerWithAttach();
		return SMTPMailerWithAttach.SMTPMailDataWithAttachment(this);
	}

	private boolean ESMTPmailDataWithAttachment() throws IOException, EncoderException {

		new ESMTPMailerWithAttach();
		return ESMTPMailerWithAttach.ESMTPmailDataWithAttachment(this);
	}

	boolean returnSent() {

		return this.sent;
	}

	public String frS() throws IOException {

		String s = this.fromMailServer.readLine();
		EmailEngine.fromServer(s);
		return s;
	}

	private static void fromServer(String s) {

		System.out.println("S: " + s);
		System.out.flush();
	}

	public void toS(String s) {

		this.toMailServer.println(s);
		this.toMailServer.flush();
		// if (s.length() < 50)
		// this.print(s);
	}

	public static void toC(String s) {

		System.out.println("- info: " + s);
		System.out.flush();
	}

	static void toErr(String s) {

		System.out.println("******ERROR****** " + s);
		System.out.flush();
	}

	public Socket getSMTPSock() { return this.SMTPSock; }

	public PrintWriter getToMailServer() { return this.toMailServer; }

	public BufferedReader getFromMailServer() { return this.fromMailServer; }

	public String getSmtp() { return this.smtp; }

	public String getLogin() { return this.login; }

	public String getPassword() { return this.plainPass; }

	public String getFrom() { return this.from; }

	public String getTo() { return this.to; }

	public String getCc() { return this.cc; }

	public String getSubject() { return this.subject; }

	public String getAttPath() { return this.attPath; }

	public String[] getBody() { return this.body; }

	public String[] getAttachmentName() { return this.attachmentName; }

	public String[] getAttachment64() { return this.attachment64; }

	public String getOneLineBody() { return this.oneLineBody; }

	public boolean relayESMTPMail(String SMTP, String login, String plainPass, String from, String to, String subject, String[] data) throws UnknownHostException, IOException {

		this.smtp = SMTP;
		this.login = login;
		this.plainPass = plainPass;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = data;

		Socket SMTPSock = new Socket(SMTP, 25);

		this.fromMailServer = new BufferedReader(new InputStreamReader(SMTPSock.getInputStream()));
		this.toMailServer = new PrintWriter(new OutputStreamWriter(SMTPSock.getOutputStream()));

		this.fromMailServer.readLine();

		// this.toC(serverLine);

		boolean sent = false;

		new ESMTPRelayEmailData();
		// Commented as some servers (123-reg) do not tell you ESMTP anymore as
		// they no longer support SMTP with no authentication
		// if (serverLine.indexOf("ESMTP") != -1) {
		sent = ESMTPRelayEmailData.ESMTPrelayData(this);

		// } else {
		// sent = this.SMTPMailData();
		// }

		SMTPSock.close();

		return sent;

	}
}
