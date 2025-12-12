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

public class StringSorter {

	public StringSorter() {

	}

	public static String[] sortNow(String[] entry) {

		int numOfEntries = entry.length;

		// This is my sorting algorithm
		String minEntry = "";
		String tempEntry = "";
		int counter = 0;
		int j = 0;

		while (counter < numOfEntries) {

			minEntry = entry[counter];

			while (j < numOfEntries) {

				if (minEntry.toLowerCase().compareTo(entry[j].toLowerCase()) > 0) {

					tempEntry = minEntry;
					minEntry = entry[j];
					entry[j] = tempEntry;
				}
				j++;
			}
			// Now we got this round's minimum
			entry[counter] = minEntry;
			counter++;
			j = counter;
		}

		// Now all addresses are sorted by domain.com
		return entry;
	}

}
