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

package org.larssentech.lib.basiclib.io.file;

import java.io.File;

class FileFilter {

	public static File[] filterDirectoryExclude(File folder, String excludeStartingWith) {

		// Raw directory contents
		String[] files = folder.list();

		// Collection to hold selected files
		File[] cleanFiles1 = new File[files.length];
		// ArrayList<File> cleanFiles = new ArrayList<File>();
		int cleanerLength = 0;

		for (int i = 0; i < files.length; i++) {

			if (!new File(files[i]).getName().startsWith(excludeStartingWith)) {

				cleanerLength++;
				cleanFiles1[i] = new File(files[i]);
			}
		}

		// Return array
		File[] cleanerFiles = new File[cleanerLength];
		for (int i = 0; i < cleanerLength; i++) cleanerFiles[i] = cleanFiles1[i];

		return cleanerFiles;
	}

}
