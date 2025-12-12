package org.larssentech.lib.basiclib2.io.parser;

import java.util.Vector;

import org.larssentech.lib.basiclib.io.parser.TSVExtractor;
import org.larssentech.lib.basiclib.toolkit.CollectionManipulationToolkit;

public class TSVExtractor2 extends org.larssentech.lib.basiclib.io.parser.TSVExtractor {

	public static String[] tsvSplit(String line) {

		Vector<String> list = new Vector<String>();

		String[] strings = line.split("\t");

		for (String string : strings)
			list.addElement(string);

		return CollectionManipulationToolkit.vector2Array1D(list);
	}

	public static String[] tsvSplit2(String line) {

		Vector<String> list = new Vector<String>();

		int prevIndex = 0;
		int index = 0;
		int tabs = 0;

		while ((index = line.indexOf("\t", prevIndex)) != -1) {

			list.add(line.substring(prevIndex, index));

			prevIndex = index + "\t".length();

			tabs++;
		}

		String last = line.substring(line.lastIndexOf("\t") + "\t".length(), line.length());
		list.add(last);

		return CollectionManipulationToolkit.vector2Array1D(list);
	}

	public static void main(String[] args) {

		new TSVExtractor();
		String[] fieldNames = TSVExtractor2.tsvSplit2(
				"0123	D132665 | 142 Howth Road	DTC	D132665	Ireland			14	0	Commercial		Dublin 5	142 Howth Road	0000000001	0			0	1		202000099362				202000001238	0-5k		Existing Business	False	AMCS ROI	CSU Bag	202000099362	FOC ACCOUNT AS PER CHRIS		");

		for (int i = 0; i < fieldNames.length; i++) {
			System.out.println(fieldNames[i]);
		}
	}
}