/**
 * 
 * Copyright (C) 2025, avanz.io (AvanzConsulting Ltd.)
 * All rights reserved.
 * 
 */

package org.larssentech.lib.basiclib.dao;

public class Query4SelectWhere extends Query4Db {

	private Filter[] filterArray;

	public Query4SelectWhere(String tableName, String[] fieldNames, Filter[] filterArray) {

		this.tableName = tableName;
		this.fieldNames = fieldNames;
		this.filterArray = filterArray;
	}

	public String makeQuery() {

		String a = "select ";

		for (int i = 0; i < this.fieldNames.length; i++) a += this.fieldNames[i] + (i < this.fieldNames.length - 1 ? ", " : "");

		String b = " from ";

		String c = this.tableName;

		String d = " WHERE ";

		// Deal with the WHERE clause
		for (int i = 0; i < this.filterArray.length; i++) d += this.filterArray[i].toString() + (i < this.filterArray.length - 1 ? " AND " : "");

		String query = a + b + c + d;

		return query;

	}

}
