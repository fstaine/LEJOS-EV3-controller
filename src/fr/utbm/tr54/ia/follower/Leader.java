package fr.utbm.tr54.ia.follower;

import java.io.Closeable;
import java.io.IOException;

import fr.utbm.tr54.ev3.RobotController;
import lejos.utility.Delay;

public class Leader implements Closeable {
	private RobotController ev3 = new RobotController();
	
	public void run() {
		while(true) {
			ev3.setSpeedRatio(0.5f);
			ev3.forward();
			Delay.msDelay(2000);
			ev3.setSpeedRatio(0f);
			ev3.forward();
			Delay.msDelay(2000);
		}
	}

	@Override
	public void close() throws IOException {
		ev3.close();
	}

	public float getSpeed() {
		return ev3.getSpeed();
	}
}
