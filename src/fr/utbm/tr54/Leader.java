package fr.utbm.tr54;

import java.io.IOException;
import java.net.UnknownHostException;

import fr.utbm.tr54.ev3.RobotController;
import fr.utbm.tr54.net.Client;
import lejos.utility.Delay;

public class Leader {
	public static void main(String[] args) throws IOException {
		try (RobotController ev3 = new RobotController()) {
			try {
				Client c = new Client(ev3, "192.168.43.98", 5555);
				c.start();
				while(true) {
					ev3.setSpeedRatio(0.4571459f);
					ev3.forward();
					Delay.msDelay(2000);
					ev3.setSpeedRatio(0f);
					ev3.forward();
					Delay.msDelay(2000);
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
}
