package fr.utbm.tr54.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.utbm.tr54.ia.follower.NetClientFollower;

public class Server extends Thread {
	private ServerSocket server;
	private volatile boolean isRunning = false;
	private NetClientFollower follower;
	
	public Server(NetClientFollower follower, int port) {
		this.follower = follower;
		try {
			server = new ServerSocket(port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		isRunning = true;
		while (isRunning == true) {
			try {
				// On attend une connexion d'un client
				Socket client = server.accept();

				// Une fois reçue, on la traite dans un thread séparé
				ClientProcessor proc = new ClientProcessor(follower, client);
				Thread serverProcessorThread = new Thread(proc);
				serverProcessorThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
			server = null;
		}
	}
}
