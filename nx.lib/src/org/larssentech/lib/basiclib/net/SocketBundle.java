/*
 * Copyright 2014-2025 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * XKomm. If not, see <http://www.gnu.org/licenses/>.
 */
package org.larssentech.lib.basiclib.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Shared class to contain all socket related objects for sending and receiving.
 * For receiving, the constructor only takes the accept() socket from the server
 * socket. For sending, the constructor takes the host and port to connect to.
 */
public class SocketBundle {

	private Socket s;
	private BufferedReader in;
	private InputStream streamIn;
	private PrintWriter out;
	private OutputStream streamOut;

	public void setStreamOut(OutputStream streamOut) { this.streamOut = streamOut; }

	private String host;
	private int port;

	public SocketBundle(Socket s) throws IOException {

		this.s = s;
		openStreams();
	}

	public SocketBundle(String host, int port) throws IOException {

		this.host = host;
		this.port = port;

	}

	public void connect() throws UnknownHostException, IOException {

		if (null == this.s) {
			this.s = new Socket(this.host, this.port);
			this.s.setSoTimeout(50000);
			openStreams();
		}
	}

	private void openStreams() throws IOException {

		this.streamIn = this.s.getInputStream();
		this.streamOut = this.s.getOutputStream();
		this.in = (new BufferedReader(new InputStreamReader(this.streamIn)));
		this.out = (new PrintWriter(new OutputStreamWriter(this.streamOut)));
	}

	private Socket getS() { return this.s; }

	private BufferedReader getIn() { return this.in; }

	public PrintWriter getOut() { return this.out; }

	public void close() throws IOException {

		this.getIn().close();
		this.getOut().close();
		this.getS().close();
	}

	private InputStream getStreamIn() throws IOException { return this.s.getInputStream(); }

	public void printOut(String string) {
		this.getOut().println(string);
		this.getOut().flush();
	}

	public OutputStream getStreamOut() { return this.streamOut; }

	public void printOut(int i) {
		this.printOut(String.valueOf(i));

	}

	public void printOut(long i) {
		this.printOut(String.valueOf(i));

	}

	public String readLineIn() throws IOException { return this.getIn().readLine(); }

	public void write(byte[] bytesRead, int i, int readCount) throws IOException {
		this.getStreamOut().write(bytesRead, 0, readCount);
		this.getStreamOut().flush();

	}

	public int read(byte[] bytesRead) throws IOException { return this.getStreamIn().read(bytesRead); }
}