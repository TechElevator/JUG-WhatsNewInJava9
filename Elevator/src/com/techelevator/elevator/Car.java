package com.techelevator.elevator;

public class Car {

	private Shaft shaft = null;
	private int currentFloor = Integer.MIN_VALUE;
	
	public Car(Shaft shaft) {
		this.shaft = shaft;
		currentFloor = this.shaft.getBottomFloor();
	}
	
	public Shaft getShaft() {
		// Create a new shaft to hand back
		return new Shaft(shaft.getBottomFloor(), shaft.getTopFloor());
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public void setCurrentFloor(int newCurrentFloor) {
		if (newCurrentFloor >= shaft.getBottomFloor() && newCurrentFloor <= shaft.getTopFloor()) {
			currentFloor = newCurrentFloor;
		}
	}
}
