package fr.utbm.tr54;

import java.io.IOException;
import java.net.UnknownHostException;

import fr.utbm.tr54.ia.follower.Leader;
import fr.utbm.tr54.net.Client;

public class MainLeader {
	public static void main(String[] args) throws IOException {
		try (Leader leader = new Leader()) {
			try {
				Client c = new Client(leader, "192.168.43.27", 5555);
				c.start();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			leader.run();
		}
	}
}
