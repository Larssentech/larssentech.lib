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

package org.larssentech.lib.basiclib.toolkit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DTK2 {

	public static boolean OLD_JRE;

	/**
	 * All time-stamps created by the XKomm2 server are based on the MySQL database
	 * and the CURRENTTIMESTAMP it creates. However, if the client is in, say Spain,
	 * the client needs to make the time-stamp be +1 Madrid time. This is done by
	 * setting the time-stamp to be "natively" Europe/London so the resulting parsed
	 * time is automatically calculated for Spain (+1).
	 * 
	 * @param string
	 * @param pattern
	 * @return
	 * @deprecated
	 */
	public static Date getDateFromString(String string, String pattern) {

		try {

			// This allows to use the MySQL time-stamp format
			// pattern (yyyy-MM-dd HH:mm:ss.'0')
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			// We received the date as it was in London but
			// Java has to make it local timezone so we have
			// to tell the date parser that the time-stamp is
			// in London time, so that it gives us the correct
			// local timezone
			TimeZone t0 = TimeZone.getTimeZone("Europe/London");
			sdf.setTimeZone(t0);

			Date d = sdf.parse(string);

			return d;

		} catch (ParseException e) {

		}

		return null;
	}

	public static Date getLocalDateFromGmtString(String string, String pattern) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			// We received the date as it was in London but
			// Java has to make it local timezone so we have
			// to tell the date parser that the time-stamp is
			// in London time, so that it gives us the correct
			// local timezone
			TimeZone t0 = TimeZone.getTimeZone("Europe/London");
			sdf.setTimeZone(t0);

			Date d = sdf.parse(string);

			return d;

		} catch (ParseException e) {

		}

		return null;
	}

	public static Date getLocalDateFromLocalString(String string, String pattern) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			Date d = sdf.parse(string);

			return d;

		} catch (ParseException e) {

		}

		return null;
	}
}
