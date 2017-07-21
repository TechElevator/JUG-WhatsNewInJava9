package com.techelevator.elevator;

import com.techelevator.drive.Motor;
import com.techelevator.drive.Idler;

public class Elevator {

	private Shaft shaft = null;
	
	public Elevator(int topFloor) {
		this(1, topFloor);
	}
	public Elevator(int bottomFloor, int topFloor) {
		shaft = new Shaft(bottomFloor, topFloor);
	}
	
	public void startUp() {
		shaft.startUp();
	}
	
	public void shutDown() {
		shaft.shutDown();
	}
	
	public boolean isRunning() {
		return shaft.isRunning();
	}
	
	public void callCar(int passengerCurrentFloor) throws InterruptedException {
		shaft.moveCar(passengerCurrentFloor);
	}

	public void rideCar(int desiredFloor) throws InterruptedException {
		shaft.moveCar(desiredFloor);
	}
}
