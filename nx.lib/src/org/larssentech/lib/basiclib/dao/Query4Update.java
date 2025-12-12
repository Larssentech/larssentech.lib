/**
 * 
 * Copyright (C) 2025, avanz.io (AvanzConsulting Ltd.)
 * All rights reserved.
 * 
 */

package org.larssentech.lib.basiclib.dao;

public class Query4Update {

	private String tableName;
	private String[] fieldNames;
	private String[] values;
	private String keyName;
	private String keyValue;

	public Query4Update(String tableName, String keyName, String keyValue, String[] fieldNames, String[] values) {
		this.tableName = tableName;
		this.fieldNames = fieldNames;
		this.values = values;
		this.keyName = keyName;
		this.keyValue = keyValue;
	}

	public String makeQuery() {

		String a = "UPDATE ";

		String b = this.tableName;

		String c = " set ";

		for (int i = 0; i < this.fieldNames.length; i++) {
			c += this.fieldNames[i] + " = " + "'" + this.values[i].replaceAll("'", "") + "'" + (i < this.fieldNames.length - 1 ? ", " : "");

		}

		String d = " WHERE " + this.keyName + " = " + "'" + this.keyValue + "'";

		String query = a + b + c + d;

		return query;
	}

}
