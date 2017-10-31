package fr.utbm.tr54;

import fr.utbm.tr54.ia.follower.NetClientFollower;
import fr.utbm.tr54.net.Server;

public class MainFollower {
	public static void main(String[] args) {
		try (NetClientFollower follower = new NetClientFollower()) {
			Server s = new Server(follower, 5555);
			s.start();
		}
	}
}
