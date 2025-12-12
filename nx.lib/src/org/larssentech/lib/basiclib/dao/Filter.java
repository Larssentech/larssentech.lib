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

package org.larssentech.lib.basiclib.dao;

public class Filter {

	public static final String LIKE = "LIKE '%";

	public static final String LIKE_CLOSE = "%'";

	private String rawFilter;

	private String symbol;

	public static final String EQUAL = "=";
	private static final String quote = "'";

	public static final String PERCENT = "%";

	public Filter(String filter) {
		this.rawFilter = filter;
		if (this.rawFilter.indexOf(EQUAL) != -1) this.symbol = EQUAL;
		if (this.rawFilter.indexOf(PERCENT) != -1) this.symbol = PERCENT;
	}

	public String getRawFilter() {
		return this.rawFilter;
	}

	public String toString() {
		return this.rawFilter;
	}

	public void setFullQuery(String fullQuery) {
		this.rawFilter = fullQuery;
	}

	public String getField() {
		return getRawFilter().substring(0, getRawFilter().indexOf(this.symbol));
	}

	public String getValue() {
		if (this.symbol.equals(PERCENT)) return getRawFilter().substring(getRawFilter().indexOf(Filter.PERCENT) + Filter.PERCENT.length());
		else
			return Filter.quote + getRawFilter().substring(getRawFilter().indexOf(Filter.EQUAL) + Filter.EQUAL.length()) + Filter.quote;
	}

	public String getSymbol() {
		return this.symbol;
	}
}