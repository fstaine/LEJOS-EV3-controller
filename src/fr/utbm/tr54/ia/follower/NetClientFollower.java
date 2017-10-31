package fr.utbm.tr54.ia.follower;

public class NetClientFollower extends AllOrNothingRobotFollower {
	
	private float leaderSpeed = -1;

	public NetClientFollower() {
		super();
	}

	@Override
	public float getNextSpeed() {
		if (leaderSpeed != -1 && !isObstacle()) {
			return leaderSpeed;
		} else {
			return super.getNextSpeed();
		}
	}

	@Override
	public String getName() {
		return "Net";
	}

	public void setLeaderSpeed(float speed) {
		this.leaderSpeed = speed;
	}
}
