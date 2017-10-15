package fr.utbm.tr54;

import fr.utbm.tr54.ia.LineFollower2;

public class TSB {
	
	public static void main(String[] args) {
		try (LineFollower2 lineFollowerRobot = new LineFollower2()) {
			lineFollowerRobot.start();
		}
	}
}
