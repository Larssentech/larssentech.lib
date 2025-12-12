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

import java.io.File;
import java.sql.Connection;

public class SimpleQueryPack {

	private Connection c;

	private String select;

	private String from;

	private String where;

	public String getQuery() {

		return this.select + this.from + this.where;
	}

	public void setSelect(String sqlSelect) {
		this.select = sqlSelect;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setWhere(String filter) {
		this.where = filter;
	}

	public Connection getConnection() {
		return this.c;
	}

	public void setConnection(Connection c) {
		this.c = c;
	}

	private File dbFile;

	public File getDbFile() {
		return this.dbFile;
	}

	public void setDbFile(File dbFile) {
		this.dbFile = dbFile;
	}
}
