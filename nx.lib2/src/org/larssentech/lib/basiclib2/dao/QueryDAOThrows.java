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

package org.larssentech.lib.basiclib2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.larssentech.lib.basiclib.dao.ConnectionPack;

public class QueryDAOThrows {

	private Connection connection;

	public static void setConnectPack(ConnectionPack connectPack) {
	}

	public QueryDAOThrows(Connection connection) {

		this.connection = connection;
	}

	public boolean dbWriteQuery(String query) throws SQLException {

		PreparedStatement myStatement = this.connection.prepareStatement(query);

		myStatement.execute();

		this.connection.close();

		return true;
	}

	public DbResponsePack dbReadQuery(String query) throws SQLException {

		// For all records... elastic
		List<String[]> result = new ArrayList<String[]>();

		// For the column headers ... fixed
		String[] header = new String[0];

		// For each record's columns... fixed
		String[] record = new String[0];

		if (null == this.connection) return new DbResponsePack(result);

		PreparedStatement myStatement = this.connection.prepareStatement(query);

		final ResultSet rec = myStatement.executeQuery();

		// Add headers first... and once only
		int cols = rec.getMetaData().getColumnCount();

		header = new String[cols];

		for (int i = 0; i < cols; i++) header[i] = rec.getMetaData().getColumnName(i + 1);

		result.add(header);

		// We expect 'n' records with 'm' columns
		while (rec.next()) {
			record = new String[cols];

			for (int m = 0; m < cols; m++) record[m] = rec.getString(m + 1);
			result.add(record);
		}

		this.connection.close();

		return new DbResponsePack(result);
	}
}