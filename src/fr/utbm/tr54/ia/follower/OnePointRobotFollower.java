package fr.utbm.tr54.ia.follower;

public class OnePointRobotFollower extends RobotFollower {
	
	private final float D = 0.05f;
	private final float a = 50f/45f;
	
	public OnePointRobotFollower() {
		super("a=50.45,d=5");
	}
	
	public String getName() {
		return "1P";
	}
	
	public float getNextSpeed() {
		float distance = ev3.distance();
		float speed = Math.max(Math.min(50, a * (distance - D)), 0);
		System.out.println(distance + ", " + speed);
		return speed;
	}
}
