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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManipulationToolkit {

	public static String getDateAndTime() {

		Calendar c1 = Calendar.getInstance();
		// c1.setTimeZone(TimeZone.getTimeZone("GMT"));

		String yyyy = c1.get(Calendar.YEAR) + "";
		String mm = (c1.get(Calendar.MONTH) + 1) + "";
		String dd = c1.get(Calendar.DAY_OF_MONTH) + "";
		String hh = c1.get(Calendar.HOUR_OF_DAY) + "";
		String min = c1.get(Calendar.MINUTE) + "";

		mm = mm.length() == 1 ? "0" + mm : mm;
		dd = dd.length() == 1 ? "0" + dd : dd;
		hh = hh.length() == 1 ? "0" + hh : hh;
		min = min.length() == 1 ? "0" + min : min;

		return dd + " " + DateManipulationToolkit.returnMMM(mm) + " " + yyyy.substring(2, 4) + " " + hh + ":" + min;

	}

	private static String returnMMM(String mm) {

		if (mm.equals("01")) return "Jan";
		if (mm.equals("02")) return "Feb";
		if (mm.equals("03")) return "Mar";
		if (mm.equals("04")) return "Apr";
		if (mm.equals("05")) return "May";
		if (mm.equals("06")) return "Jun";
		if (mm.equals("07")) return "Jul";
		if (mm.equals("08")) return "Aug";
		if (mm.equals("09")) return "Sep";
		if (mm.equals("10")) return "Oct";
		if (mm.equals("11")) return "Nov";
		if (mm.equals("12")) return "Dec";
		return null;
	}

	/**
	 * @param pattern
	 */
	public static String formatDate(Date date, String pattern) {

		if (pattern.equals("yyyy.MM.dd HH:mm")) return formatDateString4yyyyMMDDHHmm(date);
		if (pattern.equals("yyyy.MM.dd HH:mm:ss")) return formatDateString4yyyyMMDDHHmmss(date);
		if (pattern.equals("E DD MMM YY"))
			return formatDateString4DDMMMYY(date);
		else
			return "";
	}

	private static String formatDateString4yyyyMMDDHHmm(Date date) {

		// We only support this: "yyyy.MM.dd HH:mm"
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		String yyyy = c1.get(Calendar.YEAR) + "";
		String mm = (c1.get(Calendar.MONTH) + 1) + "";
		String dd = c1.get(Calendar.DAY_OF_MONTH) + "";
		String hh = c1.get(Calendar.HOUR_OF_DAY) + "";
		String min = c1.get(Calendar.MINUTE) + "";
		String ss = c1.get(Calendar.SECOND) + "";

		yyyy = yyyy.length() == 3 ? "20" + yyyy.substring(1) : yyyy;
		mm = mm.length() == 1 ? "0" + mm : mm;
		dd = dd.length() == 1 ? "0" + dd : dd;
		hh = hh.length() == 1 ? "0" + hh : hh;
		min = min.length() == 1 ? "0" + min : min;
		ss = ss.length() == 1 ? "0" + ss : ss;

		return yyyy + "." + mm + "." + dd + " " + hh + ":" + min;
	}

	private static String formatDateString4yyyyMMDDHHmmss(Date date) {

		// We only support this: "yyyy.MM.dd HH:mm"
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		String yyyy = c1.get(Calendar.YEAR) + "";
		String mm = (c1.get(Calendar.MONTH) + 1) + "";
		String dd = c1.get(Calendar.DAY_OF_MONTH) + "";
		String hh = c1.get(Calendar.HOUR_OF_DAY) + "";
		String min = c1.get(Calendar.MINUTE) + "";
		String ss = c1.get(Calendar.SECOND) + "";

		yyyy = yyyy.length() == 3 ? "20" + yyyy.substring(1) : yyyy;
		mm = mm.length() == 1 ? "0" + mm : mm;
		dd = dd.length() == 1 ? "0" + dd : dd;
		hh = hh.length() == 1 ? "0" + hh : hh;
		min = min.length() == 1 ? "0" + min : min;
		ss = ss.length() == 1 ? "0" + ss : ss;

		return yyyy + "." + mm + "." + dd + " " + hh + ":" + min + ":" + ss;
	}

	private static String formatDateString4DDMMMYY(Date date) {

		// We only support this: "yyyy.MM.dd HH:mm"
		Calendar c1 = Calendar.getInstance();

		c1.setTime(date);

		String yyyy = c1.get(Calendar.YEAR) + "";
		String mm = c1.get(Calendar.MONTH) + 1 + "";
		String dd = c1.get(Calendar.DAY_OF_MONTH) + "";

		yyyy = yyyy.length() == 3 ? "20" + yyyy.substring(1) : yyyy;
		mm = mm.length() == 1 ? "0" + mm : mm;
		dd = dd.length() == 1 ? "0" + dd : dd;

		String E = new SimpleDateFormat("E").format(date);
		String DD = dd;
		String MMM = new SimpleDateFormat("MMM").format(date);
		String YY = new SimpleDateFormat("YY").format(date);

		return E + " " + DD + " " + MMM + " " + YY;
	}

}
