/**
 * 
 * Copyright (C) 2025, avanz.io (AvanzConsulting Ltd.)
 * All rights reserved.
 * 
 */

package org.larssentech.lib.basiclib.dao;

import org.larssentech.lib.basiclib.toolkit.StringManipulationToolkit;

public class Query4Insert extends Query4Db {

	String[] values;

	public Query4Insert(String tableName, String[] fieldNames, String[] values) {

		this.tableName = tableName;
		this.fieldNames = fieldNames;
		this.values = values;
	}

	public String makeQuery() {

		String a = "insert into ";
		String b = this.tableName;

		String c = " (";
		String d = " values (";

		for (int i = 0; i < this.fieldNames.length; i++) {

			c += this.fieldNames[i] + (i < this.fieldNames.length - 1 ? ", " : "");
			d += "'" + StringManipulationToolkit.replaceAll(this.values[i], "'", "") + "'" + (i < this.fieldNames.length - 1 ? ", " : "");

		}

		c += ")";
		d += ")";

		String query = a + b + c + d;

		return query;

	}

}
