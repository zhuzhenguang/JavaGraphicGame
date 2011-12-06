package org.game.chess;

import static org.game.chess.Piece.*;
import java.awt.*;

/**
 * 裁判
 */
public class Judge {

	/**
	 * 裁判执法的比赛
	 */
	private Game game;

	/**
	 * 裁判得有规则
	 */
	private Rule rule = new Rule();

	public Judge(Game game) {
		this.game = game;
	}

	/**
	 * 是否可以下在此处
	 * 
	 * @return
	 */
	public boolean couldPlaceHere(Axis point) {
		Checker attacker = game.getAttacker();
		Checker defender = game.getDefender();
		// 自己在此处没有落子，对手也没有落子
		return !attacker.getHistory().contains(point)
				&& !defender.getHistory().contains(point);
	}

	/**
	 * 赢了?
	 * 
	 * @return 是否有赢棋局面
	 */
	public boolean isSuccessful(Axis point, Strategy strategy) {
		/*
		 * 从北-南，东-西，东北-西南，西北-东南 4个方向查找，有一个方向够数即可
		 */
		return directionFind(point, strategy, 1, 5)
				|| directionFind(point, strategy, 2, 6)
				|| directionFind(point, strategy, 3, 7)
				|| directionFind(point, strategy, 4, 8);
	}

	/**
	 * 按照一个方向上查找 direction-1:正西, 2:正北, 3:西北, 4:西南, 5:正东, 6:正南, 7:东南, 8:东北
	 * 
	 * @param point
	 *            当前的棋子
	 * @param strategy
	 *            当前的策略
	 * @param forward
	 *            当前棋子的前一个棋子
	 * @param back
	 *            当前棋子的后一个棋子
	 * @return 在一个大方向上是否够数
	 */
	private boolean directionFind(Axis point, Strategy strategy, int forward,
			int back) {
		int i = count(point, strategy, 1, forward);
		// 如果正向已经够数就返回
		if (i >= rule.getVictoryCondition()) {
			return true;
		} else {// 如果正向不够，在算反向
			return count(point, strategy, i, back) >= rule
					.getVictoryCondition();
		}
	}

	/**
	 * 递归计数，查看连珠的数目
	 * 
	 * @param point
	 * @param strategy
	 * @param i
	 * @param direction
	 * @return 在单方向上的连珠数目
	 */
	private int count(Axis point, Strategy strategy, int i, int direction) {
		Axis axis = getDirectionAxis(point.getX(), point.getY(), direction);
		// 在一个方向上递归查找
		if (strategy.contains(axis)) {
			i = count(axis, strategy, ++i, direction);
		}
		return i;
	}

}
