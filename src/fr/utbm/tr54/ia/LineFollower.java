package fr.utbm.tr54.ia;

import fr.utbm.tr54.ev3.RobotController;
import lejos.robotics.Color;

public class LineFollower implements AutoCloseable {
	
	RobotController ev3 = new RobotController();
	
	private float rotationAngle = (float) Math.PI / 12f;

	/**
	 * Start the robot:
	 * Make the robot following the line indeffinately
	 */
	public void start() {
		while(true) {
			ev3.forward();
			while (isOnLine()) {}
			
			ev3.stop();
			
			if(this.serachLine(1))
				continue;
			
			if(this.serachLine(1.5f))
				continue;
			
			if(this.serachLine(2f))
				continue;
			
			if(this.serachLine(4))
				continue;
			
			if(this.serachLine(8f))
				continue;
		}
	}
	
	/**
	 * Make the robot going backward until it reaches the line to follow
	 */
	public void backward(){
		ev3.backward();

		while (!isOnLine()) {}
		
		ev3.stop();
	}
	
	/**
	 * Turn right and then left and check if the robot in on the line afterward
	 * @param val the rotation factor
	 * @return true if on the line after the rotation, false otherwise
	 */
	public boolean serachLine(float val){
		ev3.rotate(val * rotationAngle);
		if (isOnLine()) 
			return true;

		ev3.rotate(-2f*val * rotationAngle);
		if (isOnLine()) 
			return true;
		
		return false;
	}

	/**
	 * Check if the robot is on the line to follow
	 * @return true if the robot is on the line to follow
	 */
	public boolean isOnLine() {
		return (ev3.getColor() != Color.WHITE);
	}
	
	@Override
	public void close() {
		ev3.close();
	}
}
