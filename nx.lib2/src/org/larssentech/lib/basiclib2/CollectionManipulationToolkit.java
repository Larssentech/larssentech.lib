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

package org.larssentech.lib.basiclib2;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class CollectionManipulationToolkit extends org.larssentech.lib.basiclib.toolkit.CollectionManipulationToolkit {

	public static Object[] enumerationToObjectArray(Enumeration<Object> e, int size) {

		Object[] objects = new Object[size];
		for (int i = 0; i < objects.length; i++) objects[i] = e.nextElement();
		return objects;
	}

	public static String[] enumerationToStringArray(Enumeration<String> e) {

		List<String> objects = new ArrayList<String>();
		while (e.hasMoreElements()) objects.add(e.nextElement().toString());
		return listToArray(objects);
	}

	public static String[] setToStringArray0(Set<String> set) {

		Vector<String> objects = new Vector<String>(); // new
														// String[set.size()];
		Iterator<String> ite = set.iterator();
		while (ite.hasNext()) objects.addElement(ite.next().toString());
		return toStringArray(objects);
	}

	private static String[] toStringArray(Vector<String> objects) {

		String[] array = new String[objects.size()];
		for (int i = 0; i < objects.size(); i++) array[i] = objects.get(i).toString();

		return array;
	}

	public static String[] listToArray(List<String> l) {

		String[] array = new String[l.size()];
		for (int i = 0; i < array.length; i++) array[i] = l.get(i).toString();

		return array;
	}

	public static List<Object> array2List(Object[] array) {

		ArrayList<Object> list = new ArrayList<Object>();

		for (int i = 0; i < array.length; i++) list.add(array[i]);

		return list;
	}

	public static HashMap<String, String> array2D2HashMap(String[][] array2D) {

		HashMap<String, String> hashMap = new HashMap<String, String>();

		for (int i = 0; i < array2D.length; i++) {

			String k = array2D[i][0];
			String v = array2D[i][1];

			hashMap.put(k, v);

		}

		return hashMap;
	}

	public static String[] objectArray2StringArray(Object[] array) {

		String[] returnArray = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			String s = array[i].toString();

			returnArray[i] = s;

		}

		return returnArray;
	}

}
