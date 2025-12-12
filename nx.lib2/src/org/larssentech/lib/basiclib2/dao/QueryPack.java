/*
 * Copyright 1999-2024 Larssentech Developers
 *
 * This file is part of the Larssentech BasicLib2 project.
 *
 * The Larssentech BasicLib2 Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * XKomm is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the source code.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.lib.basiclib2.dao;

import java.util.ArrayList;
import java.util.List;

import org.larssentech.lib.basiclib.dao.Query;
import org.larssentech.lib.basiclib.dao.Where;

public class QueryPack {

	private Query query;

	@Deprecated
	private Where where;
	private final List<String> substitutions = new ArrayList<String>();

	public QueryPack(Query query) {

		this.query = query;
		this.where = new Where(new String[0]);
	}

	public QueryPack(String queryString) {
		this.query = new Query(queryString);
		this.where = new Where(new String[0]);
	}

	public Query getQuery() {

		return this.query;
	}

	public void setQuery(Query query) {

		this.query = query;
	}

	public Where getWhere() {

		return this.where;
	}

	public void setWhere(Where filter) {

		this.where = filter;
	}

	public List<String> getSubstitutions() {

		return this.substitutions;
	}
}
