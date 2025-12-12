/**
 * 
 * Copyright (C) 2025, avanz.io (AvanzConsulting Ltd.)
 * All rights reserved.
 * 
 */

package org.larssentech.lib.basiclib.dao;

public class Query4Select extends Query4Db {

	public Query4Select(String tableName, String[] fieldNames) {

		this.tableName = tableName;
		this.fieldNames = fieldNames;
	}

	public String makeQuery() {

		String a = "select ";

		for (int i = 0; i < this.fieldNames.length; i++) a += this.fieldNames[i] + (i < this.fieldNames.length - 1 ? ", " : "");

		String b = " from ";

		String c = this.tableName;

		String query = a + b + c;

		return query;

	}

}
