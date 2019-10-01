package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {

	public static void main(String[] args) throws InterruptedException, NumberFormatException, IOException {

		final String serverHost = "localhost";

		Socket socketOfClient = null;

		try {

			socketOfClient = new Socket(serverHost, 9999);

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + serverHost);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + serverHost);
		}

		ConnectionHolder connectionHolder = new ConnectionHolder(socketOfClient);
		connectionHolder.start();

	}
}