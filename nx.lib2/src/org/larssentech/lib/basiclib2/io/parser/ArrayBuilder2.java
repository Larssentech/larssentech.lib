package org.larssentech.lib.basiclib2.io.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class ArrayBuilder2 extends org.larssentech.lib.basiclib.io.parser.ArrayBuilder {

	public static String[][] process2Tsv(File file, int rowsToExclude) {

		try {

			return ArrayBuilder2.process2Tsv(new FileInputStream(file), rowsToExclude);
		} catch (Exception e) {

			return new String[0][];
		}

	}

	public static String[][] process2Tsv(InputStream anInputStream, int rowsToExclude) {

		try {

			BufferedReader fromFile = new BufferedReader(new InputStreamReader(anInputStream));

			// Read away the undesired lines...
			for (int i = 0; i < rowsToExclude; i++)
				fromFile.readLine();

			// finalArray = new String[new
			// LineCounter().countLinesFrom(anInputStream) - rowsToExclude][];

			// Store the rest
			Vector aList = new Vector();
			String thisLine;
			while ((thisLine = fromFile.readLine()) != null)
				aList.addElement(thisLine);
			fromFile.close();

			String[][] finalArray = new String[aList.size()][];

			for (int i = 0; i < aList.size(); i++) {

				finalArray[i] = TSVExtractor2.tsvSplit2(aList.elementAt(i).toString());
			}

			return finalArray;
		} catch (Exception e) {

			System.out.println("ARRAY BUILDER (makeArrayFromTSVExcludeLines - FILE): " + e);
			e.printStackTrace();
			return new String[0][];
		}

	}

	public static void main(String[] args) {

		String[][] fieldNames =

				ArrayBuilder2.process2Tsv(

						new File("/Volumes/4TB Secure/Panda/FW/FS_PREPROD/import/account/sales_account.tsv"), 0);

		for (int i = 0; i < fieldNames.length; i++) {
			System.out.println(fieldNames[i]);
		}
	}

}
