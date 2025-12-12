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

package org.larssentech.lib.basiclib.toolkit;

import java.util.Vector;

public class CollectionManipulationToolkit {

	public static String[] vector2Array1D(Vector vector) {

		String[] thisRecordArray = new String[vector.size()];

		for (int i = 0; i < vector.size(); i++) thisRecordArray[i] = vector.elementAt(i).toString();

		return thisRecordArray;
	}

	public static String[][] vector2Array2D(Vector vector) {

		String[][] returnArray = new String[vector.size()][];
		for (int i = 0; i < vector.size(); i++) returnArray[i] = (String[]) vector.elementAt(i);

		return returnArray;
	}

	public static boolean entriesDiffer4Arrays(String[] array1, String[] array2) {

		if (array1.length == 0 || array2.length == 0) return true;

		if (array1.length != array2.length) return true;

		for (int i = 0; i < array1.length; i++) {

			boolean found = false;

			for (int j = 0; j < array2.length; j++)

				if (array1[i].equals(array2[j])) {
					found = true;
					break;
				}

			if (!found) return true;
		}

		for (int j = 0; j < array2.length; j++) {

			boolean found = false;

			for (int i = 0; i < array1.length; i++)

				if (array2[j].equals(array1[i])) {
					found = true;
					break;
				}

			if (!found) return true;
		}

		return false;
	}

	public static String[] joinArrays(String[] jsonFieldNames, String[] jsonCustomNames) {

		String[] combinedArray = new String[(jsonFieldNames.length + jsonCustomNames.length)];

		for (int i = 0; i < jsonFieldNames.length; i++) combinedArray[i] = jsonFieldNames[i];
		for (int i = 0; i < jsonCustomNames.length; i++) combinedArray[i + jsonFieldNames.length] = jsonCustomNames[i];

		return combinedArray;

	}
}
