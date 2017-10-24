package fr.utbm.tr54.ia.follower;

public class AllOrNothingRobotFollower extends RobotFollower {
	
	public AllOrNothingRobotFollower() {
		super("");
		ev3.setSpeedRatio(0.5f);
	}
	
	public String getName() {
		return "BIN";
	}
	
	public float getNextSpeed() {
		if (isObstacle()) {
			return 0f;
		} else {
			return 0.5f;
		}
	}
	
	private boolean isObstacle() {
		return ev3.distance() < 0.15f;
	}
}
