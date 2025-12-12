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

import org.larssentech.lib.basiclib.dao.ConnectDAO;
import org.larssentech.lib.basiclib.dao.ConnectionPack;
import org.larssentech.lib.basiclib.dao.Filter;
import org.larssentech.lib.basiclib.dao.Query;
import org.larssentech.lib.basiclib.dao.SimpleQueryPack;

public class QueryDAO {

	private static final String TOKEN = "<XX>";

	private static ConnectionPack connectPack;
	private Connection connection;
	private static Connection connx;
	private static QueryPack queryPack;

	@Deprecated
	public static ConnectionPack getConnectPack() {

		return connectPack;
	}

	public static void setConnectPack(ConnectionPack connectPack) {

		QueryDAO.connectPack = connectPack;
	}

	public QueryDAO(Connection connection) {

		this.connection = connection;
	}

	public QueryDAO(ConnectionPack pack) {

		connectPack = pack;
	}

	public static ResponsePack getXFromY(SimpleQueryPack queryPack) {

		List<String[]> result = new ArrayList<String[]>();

		String[] record = new String[0];

		try {

			if (null == queryPack.getConnection()) return null;

			PreparedStatement myStatement = queryPack.getConnection().prepareStatement(queryPack.getQuery());

			final ResultSet rec = myStatement.executeQuery();

			// For the column headers ... fixed
			String[] header = new String[0];

			// For each record's columns... fixed

			// Get column number
			int cols = rec.getMetaData().getColumnCount();

			// Add headers first... and once only

			header = new String[cols];
			for (int i = 0; i < cols; i++) header[i] = rec.getMetaData().getColumnName(i + 1);
			result.add(header);

			// We expect 'n' records with 'm' columns
			while (rec.next()) {
				record = new String[cols];

				for (int m = 0; m < cols; m++) record[m] = rec.getString(m + 1);
				result.add(record);
			}

			queryPack.getConnection().close();

			return new ResponsePack(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public ResponsePack dbGetXFromY(String query) {

		List<String[]> result = new ArrayList<String[]>();

		String[] record = new String[0];

		try {

			if (null == this.connection) return null;

			PreparedStatement myStatement = this.connection.prepareStatement(query);

			final ResultSet rec = myStatement.executeQuery();

			// For the column headers ... fixed
			String[] header = new String[0];

			// For each record's columns... fixed

			// Get column number
			int cols = rec.getMetaData().getColumnCount();

			// Add headers first... and once only

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

			return new ResponsePack(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Deprecated
	public static boolean setXinYwhereZ(ConnectionPack cPack, String query, String[] x) {

		String finalQuery = QueryComposer.getQuery(query, x);

		Connection connex = null;

		try {

			connex = ConnectDAO.connect(cPack);
			if (null == connex) return false;

			PreparedStatement myStatement = connex.prepareStatement(finalQuery);

			myStatement.execute();

			ConnectDAO.disconnect(connex);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean insertXIntoY() {

		Query finalQuery = QueryComposer.getQuery(queryPack, TOKEN);

		try {

			PreparedStatement myStatement = connx.prepareStatement(finalQuery.getQuery());

			myStatement.execute();

			ConnectDAO.disconnect(connx);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean dbInsertXIntoY(String query) {
		return dbWriteQuery(query);
	}

	public boolean dbWriteQuery(String query) {

		try {

			PreparedStatement myStatement = this.connection.prepareStatement(query);

			myStatement.execute();

			ConnectDAO.disconnect(this.connection);

			return true;

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean dbUpdateXSetYWhereZ(String query) {

		return dbWriteQuery(query);
	}

	@Deprecated
	public static ResponsePack retrieveXFromYWhereZ(String baseQuery, SelectFrom selectFrom, Filter[] filters) {

		String finalQuery = QueryComposer.getQuery(baseQuery, selectFrom, filters);

		return retrieveXFromYWhereZ(finalQuery);
	}

	public static ResponsePack getXFromYWhereZ() {

		Query finalQuery = QueryComposer.getQuery(queryPack);

		List<String[]> result = new ArrayList<String[]>();

		String[] record = new String[0];

		try {

			if (null == connx) return null;

			PreparedStatement myStatement = connx.prepareStatement(finalQuery.getQuery());

			final ResultSet rec = myStatement.executeQuery();

			// For the column headers ... fixed
			String[] header = new String[0];

			// For each record's columns... fixed

			// Get column number
			int cols = rec.getMetaData().getColumnCount();

			// Add headers first... and once only

			header = new String[cols];
			for (int i = 0; i < cols; i++) header[i] = rec.getMetaData().getColumnName(i + 1);
			result.add(header);

			// We expect 'n' records with 'm' columns
			while (rec.next()) {
				record = new String[cols];

				for (int m = 0; m < cols; m++) record[m] = rec.getString(m + 1);
				result.add(record);
			}

			ConnectDAO.disconnect(connx);

			return new ResponsePack(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Deprecated
	public static List<String[]> getXfromYwhereZ() {

		Query finalQuery = QueryComposer.getQuery(queryPack);

		List<String[]> result = new ArrayList<String[]>();

		String[] record = new String[0];

		try {

			if (null == connx) return null;

			PreparedStatement myStatement = connx.prepareStatement(finalQuery.getQuery());

			final ResultSet rec = myStatement.executeQuery();

			// Get column number
			int cols = rec.getMetaData().getColumnCount();

			// Go through all the records
			while (rec.next()) {

				// We expect each record with 'cols' column number
				record = new String[cols];

				for (int m = 0; m < cols; m++) record[m] = rec.getString(m + 1);

				result.add(record);
			}

			ConnectDAO.disconnect(connx);

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@Deprecated
	public static List<String[]> getXfromYwhereZ(ConnectionPack cPack, String query, String[] x) {

		String finalQuery = QueryComposer.getQuery(query, x);

		Connection connex = null;

		// For all records... elastic
		List<String[]> result = new ArrayList<String[]>();

		// For each record's columns... fixed
		String[] record = new String[0];

		try {

			connex = ConnectDAO.connect(cPack);

			if (null == connex) return null;

			PreparedStatement myStatement = connex.prepareStatement(finalQuery);

			final ResultSet rec = myStatement.executeQuery();

			// Get column number
			int cols = rec.getMetaData().getColumnCount();

			// Go through all the records
			while (rec.next()) {

				// We expect each record with 'cols' column number
				record = new String[cols];

				for (int m = 0; m < cols; m++) record[m] = rec.getString(m + 1);

				result.add(record);
			}

			ConnectDAO.disconnect(connex);

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Deprecated
	private static ResponsePack retrieveXFromYWhereZ(String finalQuery) {

		Connection connex = null;

		// For all records... elastic
		List<String[]> result = new ArrayList<String[]>();

		// For the column headers ... fixed
		String[] header = new String[0];

		// For each record's columns... fixed
		String[] record = new String[0];

		try {

			connex = ConnectDAO.connect(QueryDAO.connectPack);

			if (null == connex) return new ResponsePack(result);

			PreparedStatement myStatement = connex.prepareStatement(finalQuery);

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

			ConnectDAO.disconnect(connex);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			try {
				ConnectDAO.disconnect(connex);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new ResponsePack(result);
	}

	public DbResponsePack dbReadQuery(String query) {

		// For all records... elastic
		List<String[]> result = new ArrayList<String[]>();

		// For the column headers ... fixed
		String[] header = new String[0];

		// For each record's columns... fixed
		String[] record = new String[0];

		try {

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

			ConnectDAO.disconnect(this.connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			try {
				ConnectDAO.disconnect(this.connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new DbResponsePack(result);
	}

	@Deprecated
	public static ResponsePack retrieveXFromYWhereLikeZ(String baseQuery, SelectFrom selectFrom, Filter[] filters) {

		String finalQuery = QueryComposer.getLikeQuery(baseQuery, selectFrom, filters);

		return retrieveXFromYWhereZ(finalQuery);
	}

	public static void setConnx(Connection connx) {

		QueryDAO.connx = connx;
	}

	@Deprecated
	public static QueryPack getQueryPack() {

		return queryPack;
	}

	public static void setQueryPack(QueryPack queryPack) {

		QueryDAO.queryPack = queryPack;
	}

}