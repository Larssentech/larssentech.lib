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

import org.larssentech.lib.basiclib.dao.Constants;
import org.larssentech.lib.basiclib.dao.Filter;
import org.larssentech.lib.basiclib.dao.Query;
import org.larssentech.lib.basiclib.dao.Where;

public class QueryComposer {

	public static String getQuery(String baseQuery, SelectFrom selectFrom, Filter[] filters) {

		String finalQuery = baseQuery;

		/**
		 * whatWhere is an array with
		 */
		for (int i = 0; i < selectFrom.getSelect().size(); i++) finalQuery = finalQuery.replaceFirst(Constants.configSelectToken, selectFrom.getSelect().get(i).toString());

		finalQuery = finalQuery.replaceFirst(Constants.configFromToken, selectFrom.getFrom());

		for (int i = 0; i < filters.length; i++) {

			if (i == 0) finalQuery += Constants.sysWhere + filters[i].getField() + Filter.EQUAL + filters[i].getValue();
			if (i > 0) finalQuery += Constants.sysAnd + filters[i].getField() + Filter.EQUAL + filters[i].getValue();
		}
		return finalQuery;
	}

	public static String getLikeQuery(String baseQuery, SelectFrom selectFrom, Filter[] filters) {

		String finalQuery = baseQuery;

		/**
		 * whatWhere is an array with
		 */
		for (int i = 0; i < selectFrom.getSelect().size(); i++) finalQuery = finalQuery.replaceFirst(Constants.configSelectToken, selectFrom.getSelect().get(i).toString());

		finalQuery = finalQuery.replaceFirst(Constants.configFromToken, selectFrom.getFrom());

		for (int i = 0; i < filters.length; i++) {

			if (i == 0) finalQuery += Constants.sysWhere + filters[i].getField() + " " + Filter.LIKE + filters[i].getValue() + Filter.LIKE_CLOSE;
			if (i > 0) finalQuery += Constants.sysAnd + filters[i].getField() + " " + Filter.LIKE + filters[i].getValue() + Filter.LIKE_CLOSE;
		}
		return finalQuery;
	}

	public static String getQuery(String query, String[] substitutions, String queryToken) {

		String finalQuery = query;

		for (int i = 0; i < substitutions.length; i++) {

			int start = 0;
			int end = finalQuery.indexOf(queryToken);

			finalQuery = finalQuery.substring(start, end) + substitutions[i] + finalQuery.substring(end + queryToken.length(), finalQuery.length());
		}

		return finalQuery;
	}

	public static Query getQuery(QueryPack queryPack, String queryToken) {

		Query finalQuery = queryPack.getQuery();

		for (int i = 0; i < queryPack.getSubstitutions().size(); i++) {

			int start = 0;
			int end = finalQuery.getQuery().indexOf(queryToken);

			finalQuery.setQuery(
					finalQuery.getQuery().substring(start, end) + queryPack.getSubstitutions().get(i) + finalQuery.getQuery().substring(end + queryToken.length(), finalQuery.getQuery().length()));
		}

		return finalQuery;
	}

	public static String getQuery(String query, String[] substitutions) {

		String finalQuery = query;

		for (int i = 0; i < substitutions.length; i++) {

			int start = 0;
			int end = finalQuery.indexOf("<XX>");

			finalQuery = finalQuery.substring(start, end) + substitutions[i] + finalQuery.substring(end + "<XX>".length(), finalQuery.length());
		}

		return finalQuery;
	}

	public static Query getQuery(QueryPack queryPack) {

		Query query = queryPack.getQuery();
		Where where = queryPack.getWhere();

		String finalQuery = query.getQuery();

		for (int i = 0; i < where.getWhere().length; i++) {

			finalQuery = finalQuery.replaceFirst("<XX>", where.getWhere()[i]);
		}

		return new Query(finalQuery);
	}
}