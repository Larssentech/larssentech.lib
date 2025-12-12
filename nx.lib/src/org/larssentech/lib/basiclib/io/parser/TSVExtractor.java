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

package org.larssentech.lib.basiclib.io.parser;

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import org.larssentech.lib.basiclib.toolkit.CollectionManipulationToolkit;
import org.larssentech.lib.basiclib.toolkit.StringManipulationToolkit;

public class TSVExtractor {
	public TSVExtractor() {

	}

	public static String[] extractFrom(String line) {

		if (line.length() > 0) {

			String[] fieldName;

			try {

				Stack stack = new Stack();
				String thisField = "";
				int a = 0, b = 0;

				while ((a = line.indexOf("\t", b)) != -1) {

					// So field[i] will be either in this format: '"name"' or
					// just 'name'
					// Remove all " occurrences
					if ((thisField = line.substring(b, a)).indexOf("\"") != -1) {

						new StringManipulationToolkit();
						thisField = StringManipulationToolkit.replaceAll(thisField, "\"", "");
					}
					stack.addElement(thisField);
					b = a + 1;
				}
				// For the last one
				thisField = line.substring(b, line.length());

				if (thisField.indexOf("\"") != -1) {

					new StringManipulationToolkit();
					thisField = StringManipulationToolkit.replaceAll(thisField, "\"", "");
				}
				stack.addElement(thisField);

				// Dump all entries into a String[]
				fieldName = new String[stack.size()];

				while (stack.size() > 0) {
					fieldName[stack.size() - 1] = stack.pop().toString();
				}
				return fieldName;
			} catch (Exception e) {

				fieldName = new String[] { "Fatal error in TSV extraction: no TSV format?" };
				System.out.println(e);
				return fieldName;
			}
		}

		// Empty file
		else
			return new String[] { "" };

	}

	public static String[] extractIntactFrom(String line) {

		Vector list = new Vector();
		StringTokenizer tok = new StringTokenizer(line, "\t");
		while (tok.hasMoreElements())
			list.addElement((String) tok.nextElement());

		return CollectionManipulationToolkit.vector2Array1D(list);
	}

	public static void main(String[] args) {

		new TSVExtractor();
		String[] fieldNames = TSVExtractor
				.extractFrom("Date	Name	Email	Job_title	Reference	Advertised_in	Advertised_on_date");

		for (int i = 0; i < fieldNames.length; i++) {
			System.out.println(fieldNames[i]);
		}
	}
}
