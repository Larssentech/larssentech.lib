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

package org.larssentech.lib.basiclib.dao;

public interface Constants {
	public final String configAPIToken = "XX";

	public final String configSelectToken = "XX";
	public final String configFromToken = "YY";

	public final String sysVersion = "v0.1.0.1";
	public final String sysCopyright = "(c) 2023 Larssentech.org";
	public final String sysPrint0 = "xAPi3 -- Starting server socket (" + ")";
	public final String sysWarning = "PROTOTYPE 03xAB";

	public final String sysHTTP200 = "HTTP/1.1 200 OK";
	public final String sysHTTP403 = "HTTP/1.1 403 Forbidden";
	public final String sysHTTP204 = "HTTP/1.1 204 No Content";
	public final String sysHTTPContent = "Content-Type: text/json";
	public final String HTTP_ROOT = "/";
	public final String HTTP_SEP = "/";
	public final String sysHTTPDelim = " ";
	public final String sysHTTPQuestion = "?";
	public final String sysEmptyString = "";
	public final String sysLine = "-------------------------------------------";
	public final String sysWhere = " WHERE ";
	public final String sysAnd = " AND ";
	public final String sysColon = ":";

	public final String sqlGetXFromY = "SELECT " + configSelectToken + " FROM " + configFromToken + "";
	public final String sqlSelectAll = "*";

	public final String sysOutRequestBlockRequest = "Request (Raw): ";
	public final String sysOutRequestBlockActRequest = "Request (Actual): ";
	public final String sysOutRequestBlockPath = "Request Path: ";
	public final String sysOutRequestBlockSchema = "Request Schema: ";
	public final String sysOutRequestBlockObject = "Request Object: ";
	public final String sysOutRequestBlockFullFilter = "Request Filter: ";
	public final String sysOutRequestBlockFilterArray = "Filter Array: ";
	public final String sysOutRequestBlockEmpty = "<empty>";
	public final String sysOutResultMessage = "Returning lines: ";
	public final String sysDBNotThere = "DB is not reachable. Returning error message in JSON format...";
	public final String sysError = "Error";
	public final String sysOutRequestBlockMethod = "Request Method: ";
	public final String GET = "GET";
	public final String HEAD = "HEAD";

}