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

package org.larssentech.lib.basiclib.util;

public class ObjectSorter {

	public static Object[] sortNow(Object[] array) {

		int numOfEntries = array.length;

		// This is my sorting algorithm
		Object minEntry = "";
		Object tempEntry = "";
		int counter = 0;
		int j = 0;

		while (counter < numOfEntries) {

			minEntry = array[counter];

			while (j < numOfEntries) {

				if (Long.parseLong(minEntry.toString()) > Long.parseLong(array[j].toString())) {

					tempEntry = minEntry;
					minEntry = array[j];
					array[j] = tempEntry;
				}
				j++;
			}
			// Now we got this round's minimum
			array[counter] = minEntry;
			counter++;
			j = counter;
		}

		// Debug info
		for (int i = 0; i < array.length; i++) {

			// Term.pl(array[i].toString());
		}

		// Now all addresses are sorted by domain.com
		return array;
	}

}