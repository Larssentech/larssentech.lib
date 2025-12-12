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

public class XMLParser {

	public static String parseValueForTag(String XMLLine, String tagWithCorners) {

		String tagWithoutCorners = tagWithCorners.substring(1, tagWithCorners.length() - 1);

		String closingTagWithCorners = "</" + tagWithoutCorners + ">";

		if (XMLLine != null) if (XMLLine.indexOf(tagWithCorners) != -1 && XMLLine.indexOf(closingTagWithCorners) != -1)

			return XMLLine.substring(XMLLine.indexOf(tagWithCorners) + tagWithCorners.length(), XMLLine.indexOf(closingTagWithCorners));

		return "XMLError";
	}

	public static String parseValueForTag2(String XMLLine, String tagWithNoCorners) {

		String openingTagWithCorners = "<" + tagWithNoCorners + ">";
		String closingTagWithCorners = "</" + tagWithNoCorners + ">";

		if (XMLLine.indexOf(openingTagWithCorners) != -1 && XMLLine.indexOf(closingTagWithCorners) != -1)

			return XMLLine.substring(XMLLine.indexOf(openingTagWithCorners) + openingTagWithCorners.length(), XMLLine.indexOf(closingTagWithCorners));

		else return "XMLError";
	}

	public static String createXMLFor(String tagNameNoCorners, String value) {

		String xml = "";
		xml += "<" + tagNameNoCorners + ">" + value + "</" + tagNameNoCorners + ">";
		return xml;
	}

	public static String parseKeyForLine(String entireLine) {

		int start = entireLine.indexOf("<") + 1;
		int end = entireLine.indexOf(">");
		String key = entireLine.substring(start, end);
		return key;
	}

	public static boolean containsTags(String parsed) {

		// TODO Auto-generated method stub
		int openerOpen = parsed.indexOf("<");
		int openerClose = parsed.indexOf(">", openerOpen);

		int closerOpen = parsed.indexOf("</", openerClose);
		int closerClose = parsed.indexOf(">", closerOpen);

		if (openerOpen < openerClose &&

				openerClose < closerOpen &&

				closerOpen < closerClose)
			return true;

		return false;
	}

}
