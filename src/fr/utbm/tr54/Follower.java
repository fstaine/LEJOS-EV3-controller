package fr.utbm.tr54;

import fr.utbm.tr54.ev3.RobotController;
import fr.utbm.tr54.net.Server;

public class Follower {
	public static void main(String[] args) {
		try (RobotController ev3 = new RobotController()) {
			Server s = new Server(ev3, 5555);
			s.start();
		}
	}
}
