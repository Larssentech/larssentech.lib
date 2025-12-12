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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.larssentech.lib.basiclib.console.Out;

public class ConnectDAO {

	// Git test

	public static Connection connect(ConnectionPack pack) {

		if (pack.getDriverName().indexOf("mysql") != -1) return ConnectDAO.connectMySql(pack);
		else return ConnectDAO.connectDB(pack);
	}

	private static Connection connectDB(ConnectionPack pack) {

		Connection connex = null;

		try {

			Class.forName(pack.getDriverName());

			DriverManager.setLoginTimeout(3);

			String url = pack.getProtocol() + pack.getServer() + ":" + pack.getPort() + pack.getPropertyString();
			connex = DriverManager.getConnection(url, pack.getUser(), pack.getPassword());
			String userString = pack.getUser();
			String passwordString = pack.getPassword();

			connex = DriverManager.getConnection(url, userString, passwordString);

			// connex.setCatalog(pack.getSchema()); Azure don't like this
			connex.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connex;
	}

	public static void disconnect(Connection connex) {

		try {
			connex.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// String driverName, String server, String dbPort, String schema, String
	// user, String password)

	private static Connection connectMySql(ConnectionPack pack) {

		Connection connex = null;
		try {
			Class.forName(pack.getDriverName());

			DriverManager.setLoginTimeout(3);

			String url = "jdbc:mysql://" + pack.getServer() + ":" + pack.getPort() + pack.getPropertyString();
			connex = DriverManager.getConnection(url, pack.getUser(), pack.getPassword());

			connex.setCatalog(pack.getSchema());
			connex.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connex;
	}

	public static Connection connectSqlite(String dbFile) { // NO_UCD (unused
															// code)

		Connection connex = null;

		try {

			String url = "jdbc:sqlite:" + dbFile;

			connex = DriverManager.getConnection(url);
		}

		catch (SQLException e) {

			Out.pl("------------------ SQLite not avaiable: " + e.getErrorCode());
			e.printStackTrace();
		}

		return connex;
	}
}