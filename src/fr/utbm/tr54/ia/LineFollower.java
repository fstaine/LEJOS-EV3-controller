package fr.utbm.tr54.ia;

import fr.utbm.tr54.ev3.RobotController;
import lejos.hardware.Button;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class LineFollower {

	RobotController ev3 = new RobotController();
	
	private float rotationAngle = (float) Math.PI / 12f;
	
	public void start2() {
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
	
	public void backward(){
		ev3.backward();

		while (!isOnLine()) {}
		
		ev3.stop();
	}
	
	public boolean serachLine(float val){
		ev3.rotate(val * rotationAngle);
		if (isOnLine()) 
			return true;

		ev3.rotate(-4f*val * rotationAngle);
		if (isOnLine()) 
			return true;
		
		return false;
	}
	
	public void start() {
		while(Button.readButtons() != 0) {
			ev3.forward();
			while (isOnLine()) {
				Delay.msDelay(10);
			}
			
			ev3.stop();
		}
	}
	
	public boolean isOnLine() {
		return (ev3.getColor() != Color.WHITE);
	}
	
	public void shutdown() {
		ev3.shutdown();
	}
}
