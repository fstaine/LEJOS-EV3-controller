package fr.utbm.tr54;

import fr.utbm.tr54.ia.follower.*;

public class TSB {
	
	public static void main(String[] args) {
		
		
		try (RobotFollower robotFollower = new TwoPointsRobotFollower()) {
			robotFollower.run();
		}
	}
}
