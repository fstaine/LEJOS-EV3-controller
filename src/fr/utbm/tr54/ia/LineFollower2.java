package fr.utbm.tr54.ia;

import fr.utbm.tr54.ev3.RobotController;
import lejos.robotics.Color;

public class LineFollower2 implements AutoCloseable {
	RobotController ev3 = new RobotController();
	
	public void start() {
		while(true) {
			if (isOnLine()) {
				ev3.left();
			} else {
				ev3.right();
			}
		}
	}
	
	public boolean isOnLine() {
		return (ev3.getColor() != Color.WHITE);
	}
	
	@Override
	public void close() {
		ev3.close();
	}

	public void shutdown() {
		close();
	}
}
