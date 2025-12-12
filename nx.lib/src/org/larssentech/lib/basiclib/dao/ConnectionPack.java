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

public class ConnectionPack {

	private final String driverName, server, user, password;
	private String schema;

	public void setSchema(String schema) {
		this.schema = schema;
	}

	private final int port;
	private String propertyString = "";
	private String protocol;

	public void setPropertyString(String propertyString) {
		this.propertyString = propertyString;
	}

	/**
	 * @deprecated
	 * @param driverName
	 * @param server
	 * @param port
	 * @param schema
	 * @param user
	 * @param password
	 */
	public ConnectionPack(

			String driverName, String server, int port, String schema, String user, String password) {

		this.driverName = driverName;
		this.server = server;
		this.port = port;
		this.schema = schema;
		this.user = user;
		this.password = password;
	}

	public ConnectionPack(

			String driverName, String protocol, String server, int port, String schema, String user, String password) {

		this.driverName = driverName;
		this.server = server;
		this.port = port;
		this.schema = schema;
		this.user = user;
		this.password = password;
		this.protocol = protocol;
	}

	public String getServer() {
		return this.server;
	}

	public String getSchema() {
		return this.schema;
	}

	public int getPort() {
		return this.port;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public String getPropertyString() {
		return this.propertyString;
	}

	public String getProtocol() {

		return this.protocol;
	}
}