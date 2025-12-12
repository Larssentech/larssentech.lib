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

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Alert extends Dialog {

	// Top panel
	private final Panel topPanel = new Panel();
	private final Label messageLabel = new Label("");
	private TextField textField = new TextField("                        ");
	private TextField plainPassField = new TextField("              ");

	// Bottom panel
	private final Panel bottomPanel = new Panel();

	private final Button yesButton = new Button("Yes");
	private final Button noButton = new Button("No");
	private final Button closeButton = new Button("Close");

	private boolean response;

	public Alert() {

		super(new Frame());
		this.setSize(210, 100);

		this.initialise();
		this.toFront();
	}

	public Alert(Color backgroundColor, Color foregroundColor) {

		super(new Frame());
		this.setSize(210, 100);
		this.setBackground(backgroundColor);

		this.bottomPanel.setBackground(backgroundColor);

		// this.yesButton.setForeground(foregroundColor);
		// this.noButton.setForeground(foregroundColor);
		// this.closeButton.setForeground(foregroundColor);

		this.messageLabel.setForeground(foregroundColor);

		this.initialise();
		this.toFront();
	}

	/*
	 * Alert alert = new Alert(GReg.FOREGROUND_COLOR);
	 * alert.setBackground(GReg.BACKGROUND_COLOR);
	 */

	private void initialise() {

		this.setModal(true);
		this.setResizable(false);

		// We want 2 panels, CENTER and SOUTH
		this.setLayout(new BorderLayout());

		this.add(getTopPanel(), BorderLayout.CENTER);
		this.add(getBottomPanel(), BorderLayout.SOUTH);

		getTopPanel().add(getMessageLabel());
		getTopPanel().add(getTextField());
		getTopPanel().add(getPasswordField());

		getBottomPanel().add(getYesButton());
		getBottomPanel().add(getNoButton());
		getBottomPanel().add(getCloseButton());

		// Except the 2 panels
		getTopPanel().setVisible(true);
		getBottomPanel().setVisible(true);

		// make all components invisible top panel
		for (int i = 0; i < getTopPanel().getComponentCount(); i++) getTopPanel().getComponent(i).setVisible(false);

		// make all components invisible bottom panel
		for (int i = 0; i < getBottomPanel().getComponentCount(); i++) getBottomPanel().getComponent(i).setVisible(false);

		getPasswordField().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					setResponse(true);
					dispose();
				}
			}
		});

		getTextField().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					setResponse(true);
					dispose();
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {

				dispose();
			}
		});

		getCloseButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});

		getYesButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Yes
				if (e.getActionCommand().equals("Yes")) {

					setResponse(true);
					dispose();
				}

				// Find
				if (e.getActionCommand().equals("Find")) { dispose(); }
			}
		});

		getNoButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getTextField().setText("");
				getPasswordField().setText("");
				setResponse(false);
				dispose();
			}
		});

		GUITool.center(this);
	}

	/**
	 * Method to simply display a message with a Close button
	 * 
	 * @param title
	 * @param message
	 */
	public void showMessage(String title, String message) {

		this.showMessage(title, "Close", message);
	}

	/**
	 * Internal method to show a message with a button labelled by param Will
	 * assume everything was invisible and only make visible the stuff a message
	 * dialog would need leaving the rest of objects invisible
	 * 
	 * @param title
	 * @param buttonLabel
	 * @param message
	 */
	private void showMessage(String title, String buttonLabel, String message) {

		// Set title
		this.setTitle(title);

		// Set stuff visible
		this.getMessageLabel().setVisible(true);
		this.getCloseButton().setVisible(true);

		// Set our button label...
		this.getCloseButton().setLabel(buttonLabel);

		// Set text for the main message
		this.getMessageLabel().setText(message);

		// Resize
		this.pack();

		// Centre
		GUITool.center(this);

		// Show
		this.setVisible(true);
	}

	/**
	 * This is the simplest use case for the class, where we just get a request
	 * to display a message and nothing else is specified
	 * 
	 * @param string
	 */
	public void showMessage(String string) {

		this.showMessage("Alert", string);
	}

	/**
	 * This method is to ask the user to agree or disagree with something that
	 * requires a decision from the user.
	 * 
	 * @param title
	 * @param message
	 * @return
	 */
	public boolean showConfirm(String title, String message) {

		// Set title
		this.setTitle(title);

		// Set stuff visible
		this.getMessageLabel().setVisible(true);
		this.getYesButton().setVisible(true);
		this.getNoButton().setVisible(true);

		// Set texts
		this.getMessageLabel().setText(message);

		// Resize
		this.pack();

		// Centre
		GUITool.center(this);

		// Show
		this.setVisible(true);

		// Return
		return isResponse();
	}

	/**
	 * Simplest version of the request for confirmation from the user
	 * 
	 * @param string
	 * @return
	 */
	public boolean showConfirm(String string) {

		return this.showConfirm("Confirm Please...", string);
	}

	/* Getters and setters */
	private Panel getTopPanel() {

		return this.topPanel;
	}

	private Panel getBottomPanel() {

		return this.bottomPanel;
	}

	private Label getMessageLabel() {

		return this.messageLabel;
	}

	private TextField getTextField() {

		return this.textField;
	}

	private TextField getPasswordField() {

		return this.plainPassField;
	}

	private Button getYesButton() {

		return this.yesButton;
	}

	private Button getNoButton() {

		return this.noButton;
	}

	private Button getCloseButton() {

		return this.closeButton;
	}

	private boolean isResponse() {

		return this.response;
	}

	private void setResponse(boolean response) {

		this.response = response;
	}
}