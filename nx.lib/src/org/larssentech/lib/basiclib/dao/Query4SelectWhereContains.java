/**
 * 
 * Copyright (C) 2025, avanz.io (AvanzConsulting Ltd.)
 * All rights reserved.
 * 
 */

package org.larssentech.lib.basiclib.dao;

public class Query4SelectWhereContains extends Query4Db {

	private String[] contains;
	private String key;

	public Query4SelectWhereContains(String tableName, String[] fieldNames, String key, String[] contains) {

		this.tableName = tableName;
		this.fieldNames = fieldNames;
		this.key = key;
		this.contains = contains;
	}

	public String makeQuery() {

		String a = "select ";

		for (int i = 0; i < this.fieldNames.length; i++) a += this.fieldNames[i] + (i < this.fieldNames.length - 1 ? ", " : "");

		String b = " from ";

		String c = this.tableName;

		String d = " WHERE " + this.key + " IN (";

		// Deal with the WHERE clause
		for (int i = 0; i < this.contains.length; i++) d += this.contains[i] + (i < this.contains.length - 1 ? ", " : "");

		String e = ")";

		String query = a + b + c + d + e;

		return query;

	}

}
