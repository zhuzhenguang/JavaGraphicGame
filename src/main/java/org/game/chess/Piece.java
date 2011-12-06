package org.game.chess;

import static org.game.chess.Checkerboard.*;
import java.awt.*;
import java.awt.image.*;

/**
 * 棋子类
 */
public class Piece {

	/**
	 * 棋子半径
	 */
	public static final int R = 14;

	/**
	 * 棋子颜色
	 */
	private Color color;

	/**
	 * 棋子位置
	 */
	private Shape place;

	/**
	 * 初始化颜色和位置
	 * 
	 * @param place
	 * @param color
	 */
	public Piece(Shape place, Color color) {
		this.place = place;
		this.color = color;
	}

	/**
	 * 画棋子
	 * 
	 * @param g2
	 */
	public void draw(Component checkerboard) {
		Graphics2D g2 = (Graphics2D) checkerboard.getGraphics();
		g2.setPaint(color);
		g2.fill(place);
	}

	public static Axis getDirectionAxis(int x, int y, int direction) {
		switch (direction) {
		case 1:
			return getWestAxis(x, y);
		case 2:
			return getNorthAxis(x, y);
		case 3:
			return getWestNorthAxis(x, y);
		case 4:
			return getWestSouthAxis(x, y);
		case 5:
			return getEastAxis(x, y);
		case 6:
			return getSouthAxis(x, y);
		case 7:
			return getEastSouthAxis(x, y);
		case 8:
			return getEastNorthAxis(x, y);
		default:
			return getWestAxis(x, y);
		}
	}

	public static Axis getWestAxis(int x, int y) {
		return new Axis(x - UNIT, y);
	}

	public static Axis getWestNorthAxis(int x, int y) {
		return new Axis(x - UNIT, y - UNIT);
	}

	public static Axis getNorthAxis(int x, int y) {
		return new Axis(x, y - UNIT);
	}

	public static Axis getEastNorthAxis(int x, int y) {
		return new Axis(x + UNIT, y - UNIT);
	}

	public static Axis getEastAxis(int x, int y) {
		return new Axis(x + UNIT, y);
	}

	public static Axis getEastSouthAxis(int x, int y) {
		return new Axis(x + UNIT, y + UNIT);
	}

	public static Axis getSouthAxis(int x, int y) {
		return new Axis(x, y + UNIT);
	}

	public static Axis getWestSouthAxis(int x, int y) {
		return new Axis(x - UNIT, y + UNIT);
	}
}
