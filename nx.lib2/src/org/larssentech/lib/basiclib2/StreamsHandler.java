package org.larssentech.lib.basiclib2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class StreamsHandler {

	private final Socket s;
	private final BufferedReader in;
	private final PrintWriter out;

	public StreamsHandler(Socket s) throws IOException {
		this.s = s;
		this.in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
		this.out = new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
	}

	public void closeSocket() {

		try {
			this.s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readLine() throws IOException {

		return this.in.readLine();
	}

	public void println(String s2) {
		this.out.println(s2);
		this.out.flush();

	}
}