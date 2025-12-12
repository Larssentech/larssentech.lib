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

package org.larssentech.lib.basiclib.toolkit;

import java.util.Hashtable;
import java.util.Vector;

public class HashtableToolkit {

	/**
	 * Method that takes a Hashtable with key-value pairs and a String[] with the
	 * entries that need to be sorted using the Key in the Hashtable. Returns a 2D
	 * array with the key-pair entries sorted by the pattern.
	 * 
	 * @param hashtable
	 * @param sortingPattern
	 * @return
	 */
	public static String[][] hashtable2SortedArray(Hashtable hashtable, String[] sortingPattern) {

		Vector return2DArray = new Vector();

		for (int i = 0; i < sortingPattern.length; i++) {

			String value = "";

			if (null != hashtable.get(sortingPattern[i])) {

				value = hashtable.get(sortingPattern[i]).toString();

				return2DArray.addElement(new String[] { sortingPattern[i], value });
			}
		}

		String[][] ret = new String[return2DArray.size()][];

		for (int i = 0; i < return2DArray.size(); i++) {

			ret[i] = (String[]) return2DArray.elementAt(i);

		}
		return ret;
	}

	public static String toStringValues(String[][] array) {

		String str = "";

		for (int i = 0; i < array.length; i++) {

			String value = array[i][1];

			str += value;

			if (i < array.length - 1) str += ", ";
		}
		return str;
	}
}