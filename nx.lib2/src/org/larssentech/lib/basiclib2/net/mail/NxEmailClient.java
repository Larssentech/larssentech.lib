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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.larssentech.lib.basiclib.io.file.ReadBytesFromFile;
import org.larssentech.lib.basiclib.io.parser.ArrayBuilder;
import org.larssentech.lib.basiclib.settings.SettingsExtractor;

public class NxEmailClient {

	public static final String SETTINGS_PATH = ".nxmail";
	public static final String SEP = System.getProperty("file.separator");

	public static void main(String[] args) {

		try {
			System.out.println("");
			System.out.println("Larssentech Mail v3.1.2  Copyright (c) 2001-2011 NSL");
			System.out.println("UNIX/ Linux/ Windows");
			System.out.println("==================================================");
			System.out.println("");

			if (args[0].equals("-blast")) {
				new NxEmailClient();
				NxEmailClient.runMailBlast("distribution\\distribution.txt");
			}
			else {
				new NxEmailClient();
				NxEmailClient.processEmail(args[0], args[1], args[2], args[3], args[4]);
			}

		}
		catch (Exception e) {
			EmailEngine.toErr(e.toString());
			System.out.println("Bad/ missing arguments. Usage:");
			System.out.println("java NxEmailClient <to> <cc> <subject> <body> <attachment_path>");
		}
		;
	}

	private static void runMailBlast(String dataFileName) {

		new ArrayBuilder();
		String[][] dataArray = ArrayBuilder.makeArrayFromTSVExcludeLines(dataFileName, 0);
		System.out.println();

		for (int i = 0; i < dataArray.length; i++) {
			try {
				new NxEmailClient();
				NxEmailClient.processEmail(dataArray[i][2], "jeffrey.cerasuolo@nmhg.com", dataArray[i][5], dataArray[i][6], dataArray[i][3] + NxEmailClient.SEP + dataArray[i][4]);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println();

	}

	private static boolean processEmail(String to, String cc, String subject, String body, String attPath) throws IOException, IllegalStateException, EncoderException {

		if (!new File(SETTINGS_PATH).exists()) throw new FileNotFoundException("Settings file not found");

		if (new File(SETTINGS_PATH).length() == 0) throw new IOException("Settings file has size of zero");

		new SettingsExtractor();
		String smtp = SettingsExtractor.extractThis4(SETTINGS_PATH, "smtp_server");
		new SettingsExtractor();
		String login = SettingsExtractor.extractThis4(SETTINGS_PATH, "smtp_login");
		new SettingsExtractor();
		String plainPass = SettingsExtractor.extractThis4(SETTINGS_PATH, "smtp_pass");
		new SettingsExtractor();
		String from = SettingsExtractor.extractThis4(SETTINGS_PATH, "email_address");

		NxEmailClient.toC("Settings found OK. This does not guarantee they'll work.");

		if (attPath.length() == 0 || !new File(attPath).exists()) throw new IOException("Specified attachment file not found!");

		new ReadBytesFromFile();
		byte[] attachmentBytes = ReadBytesFromFile.readBytesFromFile(attPath);

		String attachEnc64 = new Base64().encode(attachmentBytes).toString();

		String fileName = attPath.substring(attPath.lastIndexOf(NxEmailClient.SEP) + 1, attPath.length());

		NxEmailClient.toC("Attachment " + fileName + " encoded with Base64...");
		NxEmailClient.toC("App has all the required data. Starting network comms...");

		return NxEmailClient.doSendMail(smtp, login, plainPass, from, to, cc, subject, body, new String[] { "", fileName }, new String[] { "", attachEnc64 });
	}

	private static boolean doSendMail(String smtp, String login, String plainPass, String from, String to, String cc, String subject, String body, String[] attachmentName, String[] attachmentBase64)
			throws IOException, EncoderException {

		NxEmailClient.toC("Creating EmailEngine instance...");

		EmailEngine ee = new EmailEngine();

		ee.sendESMTPMailWithAttachment(smtp, login, plainPass, from, to, cc, subject, body, attachmentName, attachmentBase64);
		if (ee.returnSent()) NxEmailClient.toC("Email to " + to + " was sucessful");
		else NxEmailClient.toC("Email to " + to + " FAILED !!!");

		return ee.returnSent();
	}

	public static void toC(String s) {

		System.out.println("- MSG: " + s);
		System.out.flush();
	}
}
