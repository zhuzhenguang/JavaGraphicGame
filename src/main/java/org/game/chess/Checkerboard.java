package org.game.chess;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * 棋盘类
 */
public class Checkerboard extends JPanel {

	/**
	 * 像素单位
	 */
	public static final int UNIT = 30;

	/**
	 * 横向15条线
	 */
	private static final int xNum = 15;

	/**
	 * 纵向15条线
	 */
	private static final int yNum = 15;

	/**
	 * 棋盘所有的位置，这些位置可以放置棋子
	 */
	private Set<Shape> placeList = new HashSet<Shape>(169);

	private Canvas myCanvas = null;
	private MouseListener listener = null;

	/**
	 * 构造器设定面板
	 */
	public Checkerboard() {
		super();
		setBackground(new Color(210, 105, 30));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setBorder(new LineBorder(Color.black));
		// 设置一块画布，画棋盘
		myCanvas = new MyCanvas();
		add(myCanvas);
		// 保存位置
		savePlace();
	}

	/**
	 * 保存棋子的位置
	 */
	private void savePlace() {
		for (int i = 0; i <= xNum; i++) {
			for (int j = 0; j <= yNum; j++) {
				Shape area = new Ellipse2D.Double(i * UNIT - Piece.R, j * UNIT
						- Piece.R, 28, 28);
				this.placeList.add(area);
			}
		}
	}

	public Set<Shape> getPlaceList() {
		return this.placeList;
	}

	public Canvas getCanvas() {
		return this.myCanvas;
	}

	/**
	 * 画布
	 * 
	 */
	class MyCanvas extends Canvas {

		public MyCanvas() {
			setSize(450, 450);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;

			// 画棋盘网格
			draw(g2);
		}

		/**
		 * 画线
		 * 
		 * @param g2
		 */
		private void draw(Graphics2D g2) {
			// 横向线
			for (int i = 0; i < xNum; i++) {
				g2.drawLine(0, i * UNIT, (yNum - 1) * UNIT, i * UNIT);
			}
			// 纵向线
			for (int i = 0; i < yNum; i++) {
				g2.drawLine(i * UNIT, 0, i * UNIT, (xNum - 1) * UNIT);
			}
			// 画天元和四角点
			g2.fillOval(7 * UNIT - 4, 7 * UNIT - 4, 8, 8);
			g2.fillOval(3 * UNIT - 4, 3 * UNIT - 4, 8, 8);
			g2.fillOval((xNum - 4) * UNIT - 4, 3 * UNIT - 4, 8, 8);
			g2.fillOval(3 * UNIT - 4, (yNum - 4) * UNIT - 4, 8, 8);
			g2.fillOval((xNum - 4) * UNIT - 4, (yNum - 4) * UNIT - 4, 8, 8);
		}
	}

	public void setListener(MouseListener listener) {
		this.listener = listener;
	}

	public MouseListener getListener() {
		return this.listener;
	}

}
