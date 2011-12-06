package org.game.chess;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * 玩家
 */
public class Checker {

	/**
	 * 玩家昵称
	 */
	private String nickName;

	/**
	 * 是否先手
	 */
	private boolean isFirstStrike;

	/**
	 * 步法
	 */
	private Strategy history;

	/**
	 * 大家共享一局游戏
	 */
	private static Game game = null;

	/**
	 * 初始化游戏，先手
	 * 
	 * @param game
	 * @param isFirstStrike
	 */
	public Checker(Game game, boolean isFirstStrike) {
		// 这是线程不安全的
		if (Checker.game == null) {
			Checker.game = game;
		}
		this.isFirstStrike = isFirstStrike;
		this.history = new Strategy();
	}

	/**
	 * 构造器，初始化昵称
	 * 
	 * @param game
	 * @param isFirstStrike
	 * @param nickName
	 */
	public Checker(Game game, boolean isFirstStrike, String nickName) {
		this(game, isFirstStrike);
		this.nickName = nickName;
	}

	/**
	 * 下棋
	 * 
	 * @param adversary
	 */
	public static void play() {
		// 给棋盘添加下棋事件
		final Component component = game.getCheckerboard().getCanvas();
		MouseListener listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				int key = me.getButton();

				// 点击左键黑子下，点击右键白子下(1是左键，3是右键)
				Checker adversary = (game.status.equals("black") && key == 1 ? game.checkers
						.get("white") : game.checkers.get("black"));
				// 获取鼠标点击的坐标并尝试画出棋子
				render(me.getX(), me.getY(), component, adversary);
			};

			/**
			 * 渲染棋子
			 * 
			 * @param x
			 * @param y
			 * @param component
			 * @param adversary
			 */
			private void render(int x, int y, Component component,
					Checker adversary) {
				Set<Shape> placeList = game.getCheckerboard().getPlaceList();
				for (Shape area : placeList) {
					if (area.contains(x, y)) {
						// 获得区域中点
						Axis point = getPoint(area);
						Judge judge = game.getJudge();// 获得裁判

						// 裁判可以判断是否可以下载此处
						if (judge.couldPlaceHere(point)) {
							String status = game.status;
							Color color = status.equals("black") ? Color.black
									: Color.white;
							// 初始化一个棋子，并画出
							new Piece(area, color).draw(component);
							// 记录历史
							Strategy strategy = game.checkers.get(status)
									.getHistory();
							strategy.getHistory().add(point);
							// 裁判的舞台，是否谁赢了
							if (judge.isSuccessful(point, strategy)) {
								game.end();
							} else {// 改变状态
								game.changeStatus(
										"等待" + adversary.getNickName() + "出击",
										null);
							}
							break;
						}

					}
				}
			}

			/**
			 * 获得棋子的中点
			 * 
			 * @param area
			 * @return
			 */
			private Axis getPoint(Shape area) {
				Rectangle2D bounds = area.getBounds2D();
				Axis point = new Axis((int) bounds.getX() + Piece.R,
						(int) bounds.getY() + Piece.R);
				return point;
			}

		};

		component.addMouseListener(listener);
		game.getCheckerboard().setListener(listener);

	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public Strategy getHistory() {
		return this.history;
	}

}
