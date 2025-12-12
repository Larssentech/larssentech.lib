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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class ArrayBuilder {

	public ArrayBuilder() {

	}

	public static String[][] makeArrayFromTSVExcludeLines(String fileName, int rowsToExclude) {

		return ArrayBuilder.makeArrayFromTSVExcludeLines(new File(fileName), rowsToExclude);

	}

	public static String[][] makeArrayFromIntactTSVExcludeLines(String fileName, int rowsToExclude) {

		return ArrayBuilder.makeArrayFromIntactTSVExcludeLines(new File(fileName), rowsToExclude);

	}

	private static String[][] makeArrayFromTSVExcludeLines(File anInputStream, int rowsToExclude) {

		try {

			return ArrayBuilder.makeArrayFromTSVExcludeLines(new FileInputStream(anInputStream), rowsToExclude);
		}
		catch (Exception e) {

			return new String[0][];
		}

	}

	private static String[][] makeArrayFromIntactTSVExcludeLines(File anInputStream, int rowsToExclude) {

		try {

			return ArrayBuilder.makeArrayFromIntactTSVExcludeLines(new FileInputStream(anInputStream), rowsToExclude);
		}
		catch (Exception e) {

			return new String[0][];
		}

	}

	public static String[][] makeArrayFromTSVExcludeLines(InputStream anInputStream, int rowsToExclude) {

		try {

			BufferedReader fromFile = new BufferedReader(new InputStreamReader(anInputStream));

			// Read away the undesired lines...
			for (int i = 0; i < rowsToExclude; i++) fromFile.readLine();

			// finalArray = new String[new
			// LineCounter().countLinesFrom(anInputStream) - rowsToExclude][];

			// Store the rest
			Vector aList = new Vector();
			String thisLine;
			while ((thisLine = fromFile.readLine()) != null) aList.addElement(thisLine);
			fromFile.close();

			String[][] finalArray = new String[aList.size()][];
			for (int i = 0; i < aList.size(); i++) finalArray[i] = TSVExtractor.extractFrom(aList.elementAt(i).toString());

			return finalArray;
		}
		catch (Exception e) {

			System.out.println("ARRAY BUILDER (makeArrayFromTSVExcludeLines - FILE): " + e);
			e.printStackTrace();
			return new String[0][];
		}

	}

	private static String[][] makeArrayFromIntactTSVExcludeLines(InputStream anInputStream, int rowsToExclude) {

		try {

			BufferedReader fromFile = new BufferedReader(new InputStreamReader(anInputStream));

			// Read away the undesired lines...
			for (int i = 0; i < rowsToExclude; i++) fromFile.readLine();

			// finalArray = new String[new
			// LineCounter().countLinesFrom(anInputStream) - rowsToExclude][];

			// Store the rest
			Vector aList = new Vector();
			String thisLine;
			while ((thisLine = fromFile.readLine()) != null) aList.addElement(thisLine);
			fromFile.close();

			String[][] finalArray = new String[aList.size()][];
			for (int i = 0; i < aList.size(); i++) finalArray[i] = TSVExtractor.extractIntactFrom(aList.elementAt(i).toString());

			return finalArray;
		}
		catch (Exception e) {

			System.out.println("ARRAY BUILDER (makeArrayFromTSVExcludeLines - FILE): " + e);
			e.printStackTrace();
			return new String[0][];
		}

	}

}
