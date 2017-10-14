package fr.utbm.tr54.ia;

import fr.utbm.tr54.ev3.RobotController;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class LineFollower2 implements AutoCloseable {
	RobotController ev3 = new RobotController();
	int flagON = 0;
	int flagOFF = 0;
	
	public void start() {
		while(true) {
			if (isOnLine()) {
				flagOFF=0;
				flagON++;
				ev3.left(flagON);
			} else {
				flagON=0;
				flagOFF++;
				ev3.right(flagOFF);
			}
			Delay.msDelay(5);
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
