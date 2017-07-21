package com.techelevator.elevator;

import com.techelevator.drive.Idler;
import com.techelevator.drive.Motor;

public class Shaft {

	private static final int IDLER_LOOPS_PER_FLOOR = 5;

	private Motor motor = new Motor();
	private Idler idler = new Idler();
	private int bottomFloor;
	private int topFloor;
	private Car car;

	public Shaft(int bottomFloor, int topFloor) {
		this.bottomFloor = bottomFloor;
		this.topFloor = topFloor;
		car = new Car(this);
	}
	
	public int getBottomFloor() {
		return bottomFloor;
	}
	
	public int getTopFloor() {
		return topFloor;
	}
	
	public void startUp() {
		motor.turnOn();
	}
	
	public void shutDown() {
		motor.turnOff();
	}
	
	public boolean isRunning() {
		return motor.isOn();
	}

	public void moveCar(int targetFloor) throws InterruptedException {
		while(car.getCurrentFloor() != targetFloor) {
			if (car.getCurrentFloor() < targetFloor) {
				for(int i = 0; i < IDLER_LOOPS_PER_FLOOR; i++) {
					idler.up();
				}
				car.setCurrentFloor(car.getCurrentFloor() + 1);
			}
			else {
				for(int i = 0; i < IDLER_LOOPS_PER_FLOOR; i++) {
					idler.down();
				}
				car.setCurrentFloor(car.getCurrentFloor() - 1);
			}
			System.out.println("Current Floor: " + car.getCurrentFloor());
		}
		idler.idle();

	}

}
