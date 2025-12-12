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

package org.larssentech.lib.basiclib.util;

public class LoginChecker {

	public static final char ECHO = '*';

	public static boolean validateEmail(String email) {

		boolean notShit = notShit(email);
		boolean atIstGut = atIstGut(email);
		boolean dotIstGut = dotIstGut(email);

		return notShit && atIstGut && dotIstGut;

	}

	private static boolean dotIstGut(String email) {

		// We need a dot too... at least one after the @
		int dot = email.indexOf(".");

		// ...This is the Goldielocks class...
		// Not at the start... nor the end...
		boolean dotIs = dot != -1;
		boolean dotNotAtStart = !email.startsWith("."); // a.pollunez@b.com
		boolean dotNotAtEnd = !email.endsWith(".");

		int at = email.indexOf("@");
		boolean dot2Is = email.indexOf(".", at) > at;

		return dotIs && dotNotAtStart && dotNotAtEnd && dot2Is;
	}

	private static boolean atIstGut(String email) {

		// So we need @
		int at = email.indexOf("@");

		// Not at the start or the end
		boolean atIs = at != -1;
		boolean atNotAtStart = at > 0; // a@b.com
		boolean atNotAtEnd = (at < email.length());
		boolean atOnce = email.indexOf("@", at + 1) == -1;

		return atIs && atNotAtStart && atNotAtEnd && atOnce;
	}

	private static boolean notShit(String email) {

		if (null == email) return false;
		boolean notNull = !"null".equals(email);
		boolean notShorty = email.length() >= 5;
		return notNull && notShorty;
	}

	public static boolean validatePassword(String pass) {

		return (null != pass) && pass.length() > 0;
	}

	public static boolean validateHardPassword(String pass) {

		String nums = "1234567890";
		String syms = "!\"@#$%&/()=?+*{}[^]-_.:,;<>";

		boolean passHasNums = false;
		boolean passHasSyms = false;

		for (int i = 0; i < pass.length(); i++) for (int j = 0; j < nums.length(); j++) if (pass.charAt(i) == nums.charAt(j)) { passHasNums = true; break; }

		for (int i = 0; i < pass.length(); i++) for (int j = 0; j < syms.length(); j++) if (pass.charAt(i) == syms.charAt(j)) { passHasSyms = true; break; }

		return (null != pass) && pass.length() > 6 && passHasNums && passHasSyms;
	}

	public static boolean isGood(String login) {

		return null != login && login.length() > 0 && !login.equals("Not Set");
	}
}
