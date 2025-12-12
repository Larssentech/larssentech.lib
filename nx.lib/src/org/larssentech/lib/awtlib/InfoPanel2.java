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
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InfoPanel2 extends Dialog {

	private static final long serialVersionUID = 1L;

	private TextArea textArea; // = new TextArea("", 27, 52,
								// TextArea.SCROLLBARS_VERTICAL_ONLY);

	public InfoPanel2(int i, int j) {

		super(new Frame());
		this.textArea = new TextArea("", i, j, TextArea.SCROLLBARS_VERTICAL_ONLY);
	}

	private static void center(Component f) {

		f.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - f.getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - f.getSize().height) / 2);
	}

	public void showInfo(String title, String message) {

		this.showMessage(title, message);
	}

	private void buildAndWait(String title, String message) {

		this.setResizable(false);
		this.setLayout(new FlowLayout());

		this.textArea.setEditable(false);
		this.add(this.textArea);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {

				setVisible(false);
			}
		});

		// Set title
		this.setTitle(title);

		// Set texts
		this.textArea.setText(message);

		// Resize
		this.pack();

		// Centre
		InfoPanel2.center(this);
	}

	private void showMessage(String title, String message) {

		this.buildAndWait(title, message);

		// Show
		this.setVisible(true);
	}

}