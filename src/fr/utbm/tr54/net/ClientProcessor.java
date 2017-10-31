package fr.utbm.tr54.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

import fr.utbm.tr54.ia.follower.NetClientFollower;

public class ClientProcessor implements Runnable {
	
	public final Socket sock;
	private BufferedInputStream reader = null; // buffer de lecture
	private NetClientFollower follower;

	
	public ClientProcessor(NetClientFollower follower, Socket sock) {
		this.sock = sock;
		this.follower = follower;
		try {
			reader = new BufferedInputStream(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("Une connexion d'un client a ete recue.");
		while (!sock.isClosed()) {
			try {
				// On attend la demande du client
				String request = getRequest();
				System.out.println(request);

				// set speed from the leader
				follower.setLeaderSpeed(Float.parseFloat(request));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	};
	
	private String getRequest() throws IOException {
		String response = "";
		byte[] b = new byte[1];
		while (!response.contains(";")) {
			int count = reader.read(b);
			if (count <= 0 ) {
				return "0;";
			}
			response += new String(b, 0, count);
		}
		return response.replace(";", "");
	}
}
