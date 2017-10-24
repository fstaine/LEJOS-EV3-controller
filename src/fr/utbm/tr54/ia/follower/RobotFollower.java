package fr.utbm.tr54.ia.follower;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.utbm.tr54.ev3.RobotController;
import lejos.utility.Delay;

public abstract class RobotFollower implements AutoCloseable {
	protected RobotController ev3 = new RobotController();
	
	private FileOutputStream out;
	
	private int msDelay = 20;
	
	public RobotFollower(String other) {
		try {
			out = new FileOutputStream(new File("TSB_" + getName() + "_" + other + ".csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public void run() {
		while(true) {
			float speed = getNextSpeed();
			ev3.setSpeedRatio(speed);
			ev3.forward();
			saveData(ev3.distance(), speed);
			Delay.msDelay(msDelay);
		}
	}
	
	public abstract float getNextSpeed();
	
	public abstract String getName();
	
	@Override
	public void close() {
		ev3.close();
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void saveData(float distance, float speed) {
		try {
			out.write((Float.toString(distance) + ";" + Float.toString(speed) + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
