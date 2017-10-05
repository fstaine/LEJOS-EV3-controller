package fr.utbm.tr54;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.utbm.tr54.ev3.RobotController;
import fr.utbm.tr54.ia.LineFollower;
import lejos.utility.Delay;

public class AAA {
	
	public static void main(String[] args) {
		LineFollower lineFollowerRobot = new LineFollower();
		lineFollowerRobot.start2();
		lineFollowerRobot.shutdown();
		
		Delay.msDelay(5000);
	}
	
	public static void main2(String[] args) throws IOException {
		FileOutputStream out = new FileOutputStream(new File("fstaine.csv"));
		RobotController ev3 = new RobotController();

		System.out.println("Test1\n");
		for(int i=0;i<10;i++) {
			float dist = ev3.distance();
			System.out.println(dist);
			write(out, Float.toString(dist) + "\n");
			Delay.msDelay(200);
		}
		
		System.out.println("Test2: n=2\n");
		for(int i=0;i<10;i++) {
			float dist = ev3.distance(2);
			System.out.println(dist);
			write(out, Float.toString(dist) + "\n");
			Delay.msDelay(200);
		}
		
		System.out.println("Test3: n=5\n");
		for(int i=0;i<10;i++) {
			float dist = ev3.distance(5);
			System.out.println(dist);
			write(out, Float.toString(dist) + "\n");
			Delay.msDelay(200);
		}
		
		System.out.println("Test4: n=10\n");
		for(int i=0;i<10;i++) {
			float dist = ev3.distance(10);
			System.out.println(dist);
			write(out, Float.toString(dist) + "\n");
			Delay.msDelay(200);
		}
		
		out.close();
		ev3.shutdown();
	}
	
	public static void write(FileOutputStream out, String str) throws IOException {
		out.write(str.getBytes());
	}
}
