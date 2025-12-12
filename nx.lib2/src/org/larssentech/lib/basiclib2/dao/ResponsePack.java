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

import java.util.ArrayList;
import java.util.List;

public class ResponsePack {

	private final List<String[]> list;
	private String json;
	private String object;

	private int counter = 0;

	public ResponsePack(List<String[]> list) {

		this.list = list;
	}

	public ResponsePack() {

		this.list = new ArrayList<String[]>();
	}

	public int getSize() {

		return this.list.size() - 1;
	}

	public void add(String[] line) {

		this.list.add(line);
	}

	public String[] get(int i) {

		return (String[]) this.list.get(i);
	}

	public void setJson(String string) {

		this.json = string;

	}

	public String[] getHeaders() {
		this.counter++;
		return this.list.get(0);
	}

	public String getJson() {
		return this.json;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String[] getNextRecord() {

		String[] nextRecord = this.list.get(this.counter);
		this.counter++;

		return nextRecord;
	}
}
