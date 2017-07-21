package com.techelevator.elevator.drive;

public class Motor {

	private boolean on = false;
	
	public boolean isOn() {
		return on;
	}
	
	public boolean turnOn() {
		return (on = true) == true;
	}
	
	public boolean turnOff() {
		return (on = false) == true;
	}
	
}
