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

import java.awt.Component;
import java.awt.Toolkit;

public class GUITool {

	public static void center(Component f) {

		f.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - f.getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - f.getSize().height) / 2);

	}

}
