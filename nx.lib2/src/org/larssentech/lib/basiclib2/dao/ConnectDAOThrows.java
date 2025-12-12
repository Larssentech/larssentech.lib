/**
 * 
 * Copyright (C) 2025, avanz.io (AvanzConsulting Ltd.)
 * All rights reserved.
 * 
 */

package org.larssentech.lib.basiclib2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.larssentech.lib.basiclib.dao.ConnectionPack;

public class ConnectDAOThrows {

	public static Connection connect(ConnectionPack pack) throws ClassNotFoundException, SQLException {

		if (pack.getDriverName().indexOf("mysql") != -1) return ConnectDAOThrows.connectMySql(pack);
		else return connectDB(pack);
	}

	private static Connection connectDB(ConnectionPack pack) throws ClassNotFoundException, SQLException {

		Connection connex = null;

		Class.forName(pack.getDriverName());

		DriverManager.setLoginTimeout(3);

		String url = pack.getProtocol() + pack.getServer() + ":" + pack.getPort() + pack.getPropertyString();
		connex = DriverManager.getConnection(url, pack.getUser(), pack.getPassword());
		String userString = pack.getUser();
		String passwordString = pack.getPassword();

		connex = DriverManager.getConnection(url, userString, passwordString);

		// connex.setCatalog(pack.getSchema());
		connex.setAutoCommit(true);

		return connex;
	}

	private static Connection connectMySql(ConnectionPack pack) throws ClassNotFoundException, SQLException {

		Connection connex = null;

		Class.forName(pack.getDriverName());

		DriverManager.setLoginTimeout(3);

		String url = "jdbc:mysql://" + pack.getServer() + ":" + pack.getPort() + pack.getPropertyString();
		connex = DriverManager.getConnection(url, pack.getUser(), pack.getPassword());

		connex.setCatalog(pack.getSchema());
		connex.setAutoCommit(true);

		return connex;
	}
}