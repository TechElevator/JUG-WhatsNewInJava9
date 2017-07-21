package com.techelevator.elevator.drive;

public class Idler {

	private static final long TIME_DELAY = 100;
	
	private Direction direction = Direction.IDLE;
	
	public void idle() {
		direction = Direction.IDLE;
	}
	
	public int up() throws InterruptedException {
		// Proceed if idle, OR already moving up
		if (direction == Direction.IDLE || direction == Direction.UP) {
			direction = Direction.UP;
			Thread.sleep(TIME_DELAY);
			return 1;
		}
		return 0;
	}
	
	public int down() throws InterruptedException {
		// Proceed if idle, OR already moving down
		if (direction == Direction.IDLE || direction == Direction.DOWN) {
			direction = Direction.DOWN;
			Thread.sleep(TIME_DELAY);
			return -1;
		}
		return 0;
	}
	
}
