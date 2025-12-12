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

package org.larssentech.lib.awtlib;

import java.awt.Dimension;
import java.awt.Point;

import org.larssentech.lib.basiclib.settings.SettingsExtractor;
import org.larssentech.lib.basiclib.settings.SettingsUpdater;

public class DisplayTool2 {

	private static final String X_LOC = "X_LOC";
	private static final String Y_LOC = "Y_LOC";
	private static final String X_DIM = "X_DIM";
	private static final String Y_DIM = "Y_DIM";

	public static boolean saveDimension(String fileName, Dimension dimension) {

		boolean b1 = SettingsUpdater.updateLine(fileName, X_DIM, "" + dimension.width);

		boolean b2 = SettingsUpdater.updateLine(fileName, Y_DIM, "" + dimension.height);

		return b1 && b2;
	}

	public static Dimension readDimension(String fileName) {

		try {

			String i = SettingsExtractor.extractThis4(fileName, X_DIM);

			String j = SettingsExtractor.extractThis4(fileName, Y_DIM);

			return new Dimension(Integer.parseInt(i), Integer.parseInt(j));
		}

		catch (Exception e) {

			return new Dimension(0, 0);
		}
	}

	public static Point readLocation(String fileName) {

		try {

			String i = SettingsExtractor.extractThis4(fileName, X_LOC);
			String j = SettingsExtractor.extractThis4(fileName, Y_LOC);
			return new Point(Integer.parseInt(i), Integer.parseInt(j));
		}
		catch (Exception e) {

			return new Point(0, 0);
		}
	}

	public static boolean saveLocation(String fileName, Point location) {

		boolean b1 = SettingsUpdater.updateLine(fileName, X_LOC, "" + location.x);
		boolean b2 = SettingsUpdater.updateLine(fileName, Y_LOC, "" + location.y);

		return b1 && b2;
	}
}