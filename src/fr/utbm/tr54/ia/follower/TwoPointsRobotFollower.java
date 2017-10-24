package fr.utbm.tr54.ia.follower;

public class TwoPointsRobotFollower extends RobotFollower {
	
	private float prevSpeed = 0.5f;
	
	private final float D = 0.05f;
	private final float a = 50f/45f;
	
	public TwoPointsRobotFollower() {
		super("a=50.45,d=5");
	}
	
	public String getName() {
		return "2P";
	}
	
	public float getNextSpeed() {
		float distance = ev3.distance();
		float speed = Math.min(Math.max(2.5f * (distance - 20f), Math.min(Math.max(a * (distance - D), 0f), prevSpeed)), 50f);
		System.out.println(ev3.distance() + ", " + speed);
		prevSpeed = speed;
		return speed;
	}
}
