package com.techelevator;

import java.util.Scanner;

import com.techelevator.elevator.*;

public class Building {

	public static void main(String[] args) throws InterruptedException {
	
		Elevator elevator = new Elevator(1, 10);
		elevator.startUp();
		System.out.println("The elevator has been started up.");
		
		Scanner input = new Scanner(System.in);
		while (elevator.isRunning()) {
			
			System.out.println("Enter your current floor to call evelator (0 to shut down): ");
			int currentFloor = Integer.parseInt(input.nextLine());
			if (currentFloor == 0) { elevator.shutDown(); break; }
			elevator.callCar(currentFloor);
			
			System.out.println("The elevator has arrived.\nEnter your desired floor: ");
			int desiredFloor = Integer.parseInt(input.nextLine());
			elevator.rideCar(desiredFloor);
			
			System.out.println("The elevator has arrived at your desired floor.\n===============================================");

		}
		
		System.out.println("The elevator has been shut down.");
		
	}
	
}
