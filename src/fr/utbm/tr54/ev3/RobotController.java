package fr.utbm.tr54.ev3;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class RobotController {
	EV3LargeRegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.B);
	EV3LargeRegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.C);
	
	EV3UltrasonicSensor dist = new EV3UltrasonicSensor(SensorPort.S2);
	EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
	
	SampleProvider distanceSampleProvider;
	
	public RobotController() {
		left.setAcceleration(1000);
		right.setAcceleration(1000);
		left.setSpeed(300);
		right.setSpeed(300);
	}
	
	public void enableDist() {
		dist.enable();
	}
	
	public void disableDist() {
		dist.disable();
	}
	
	/**
	 * Move the robot forward
	 */
	public void forward() {

		left.forward();
		right.forward();
	}
	
	/**
	 * Move the robot backward
	 */
	public void backward() {

		left.backward();
		right.backward();
	}
	
	/**
	 * Rotate the robot according to a certain angle
	 * @param rad angle to rotate in rad
	 */
	public void rotate(float rad, boolean immediate_return) {
		float rotation_factor = 2; 
		left.rotate(toDeg(-rad * rotation_factor), true);
		right.rotate(toDeg(rad * rotation_factor), immediate_return);
	}
	
	/**
	 * Rotate the robot accoring to a certain angle, returns at the end of the rotation
	 * @param rad angle to rotate in rad
	 */
	public void rotate(float rad) {
		rotate(rad, false);
	}
	
	/**
	 * Stop the robot
	 */
	public void stop() {
		left.stop(true);
		right.stop();
	}
	
	public float distance() {
		float sample[] = new float[1];
		dist.getDistanceMode().fetchSample(sample, 0);
		return sample[0];
	}
	
	public float distance(int n) {
		float sample[] = new float[n];
		if (distanceSampleProvider == null) {
			distanceSampleProvider = dist.getDistanceMode();
		}
		for (int i=0;i<n;i++) {
			// FIXME: get every time the same value ??
			float val[] = new float[1];
			distanceSampleProvider.fetchSample(val, 0);
			sample[i] = val[0];
			// Delay.msDelay(10);
		}
		return mean(sample);
	}
	
	public int getColor() {
		int c = color.getColorID();
		System.out.println(c);
		return c;
	}
	
	public void shutdown() {
		dist.close();
		color.close();
		stop();
	}
	
	/**
	 * Convert Radians to Degrees
	 * @param rad angle in rad
	 * @return angle in deg
	 */
	private int toDeg(float rad) {
		return (int) (rad * 360 / (2*Math.PI));
	}
	
	private float mean(float vals[]) {
		float res = 0;
		for (float f : vals) {
			System.out.println(f);
			res += f;
		}
		return res / vals.length;
	}
}