package fr.utbm.tr54.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.utbm.tr54.ia.follower.Leader;

public class Client extends Thread {

	private Socket connexion = null;
	private PrintWriter writer = null;
	
	private volatile boolean isRunning = false;
	
	Leader leader;

	public Client(Leader leader, String host, int port) throws UnknownHostException, IOException {
		this.leader = leader;
		connexion = new Socket(host, port);
		writer = new PrintWriter(connexion.getOutputStream(), true);
	}
	
	public void run() {
		isRunning = true;
		
		while (isRunning) {
			// On envoie la commande au serveur
			writer.write("" + leader.getSpeed() + ";");
			writer.flush();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		writer.close();
		try {
			connexion.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		isRunning = false;
	}
}
