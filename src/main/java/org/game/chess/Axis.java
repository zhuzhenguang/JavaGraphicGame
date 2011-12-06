package org.game.chess;

public class Axis {

	private int x;
	private int y;

	public Axis(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Axis) obj).getX() == this.x && ((Axis) obj).getY() == this.y;
	}
}
