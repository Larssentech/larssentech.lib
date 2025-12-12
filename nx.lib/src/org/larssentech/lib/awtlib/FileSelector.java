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

package org.larssentech.lib.awtlib;

import java.awt.FileDialog;
import java.awt.Frame;

public class FileSelector {

	/**
	 * Method to allow the user to select a file graphically
	 * 
	 * @return full name of the file to stream
	 */
	public static String getFile(Frame owner) {

		// Boilerplate stuff
		final FileDialog fileDialog;
		fileDialog = new FileDialog(owner, "" + "");
		fileDialog.setModal(true);
		fileDialog.setVisible(true);

		// If file is good... return the full name
		if (null != fileDialog.getDirectory()) return fileDialog.getDirectory() + fileDialog.getFile();

		// ...or not
		return "";
	}

}
