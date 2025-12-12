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

package org.larssentech.lib.basiclib.settings;

import java.io.File;

import org.larssentech.lib.basiclib.io.text.ReadFromFile;
import org.larssentech.lib.basiclib.io.text.SaveToFile;

public class SettingsUpdater {
	public SettingsUpdater() {

	}

	public static boolean updateLine(String fileName, String field, String value) {

		if (!(new File(fileName)).exists()) { SaveToFile.saveToFile(fileName, new String[] { "[SETTINGS_DO_NOT_EDIT]" }, false); }
		String settings[] = ReadFromFile.readFromFile(fileName);

		for (int i = 0; i < settings.length; i++) {

			if (settings[i].indexOf(field) == 0) {

				settings[i].substring(field.length() + 1);
				settings[i] = String.valueOf((new StringBuffer(String.valueOf(field))).append("=").append(value));
				return SaveToFile.saveToFile(fileName, settings, false);
			}
		}

		return SaveToFile.saveToFile(fileName, new String[] { String.valueOf((new StringBuffer(String.valueOf(field))).append("=").append(value)) }, true);
	}

	public static boolean exists(String fileName) {

		return new File(fileName).exists();
	}

}
