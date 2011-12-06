package org.game.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 开始一局游戏
 */
public class Game {

	/**
	 * 一局游戏中的状态(该谁走了)
	 */
	public static String status = "white";

	/**
	 * 一名裁判，两名选手
	 */
	private Judge judge = null;
	public static Map<String, Checker> checkers = new HashMap<String, Checker>(
			2);

	/**
	 * 胜利者
	 */
	private Checker winner = null;

	/**
	 * 棋盘，控制面板和错误标语
	 */
	private Checkerboard checkerboard = null;
	private Navboard navboard = null;
	private static JDialog dialog = null;

	/**
	 * 初始化棋盘
	 * 
	 * @param checkerboard
	 */
	public Game(Checkerboard checkerboard) {
		this.checkerboard = checkerboard;
		if (dialog == null) {
			JOptionPane messagePane = new JOptionPane(
					"attacker or defender is empty!", JOptionPane.ERROR_MESSAGE);
			dialog = messagePane.createDialog(checkerboard, "Error");
		}
	}

	/**
	 * 初始化面板
	 * 
	 * @param checkerboard
	 * @param navboard
	 */
	public Game(Checkerboard checkerboard, Navboard navboard) {
		this(checkerboard);
		this.navboard = navboard;
	}

	/**
	 * 初始化玩家等
	 */
	public void init() {
		final JButton startButton = navboard.getStartButton();
		// 获取第二个组件，是文本输入框
		final Component attackerField = navboard.getAttackerBox().getComponent(
				1);
		final Component defenderField = navboard.getDefenderBox().getComponent(
				1);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				start((JTextField) attackerField, (JTextField) defenderField);
			}
		});
		initJudge();
	}

	/**
	 * 初始化裁判
	 */
	private void initJudge() {
		if (this.judge == null) {
			this.judge = new Judge(this);
		}
	}

	/**
	 * 游戏开始
	 * 
	 * @param attackerField
	 * @param defenderField
	 */
	private void start(JTextField attackerField, JTextField defenderField) {
		String attackerName = attackerField.getText();
		String defenderName = defenderField.getText();

		if (attackerName == null || "".equals(attackerName.trim())
				|| defenderName == null || "".equals(defenderName.trim())) {
			dialog.setVisible(true);
			return;
		}

		// 显示一对选手
		displayNames(attackerName, defenderName);

		// 初始化挑战者和守擂者，然后2个人开始下棋
		checkers.put("black", new Checker(this, true, attackerName));
		checkers.put("white", new Checker(this, false, defenderName));
		changeStatus("等待" + attackerName + "进攻", null);
		Checker.play();
		navboard.getStartButton().setEnabled(false);
	}

	/**
	 * 改变游戏状态
	 * 
	 * @param message
	 */
	public void changeStatus(String message, Font font) {
		// 让出下棋权给对方
		Game.status = "black".equals(Game.status) ? "white" : "black";
		JLabel statusLabel = navboard.getStatus();
		if (font != null) {
			statusLabel.setFont(font);
		}
		statusLabel.setText(message);
	}

	/**
	 * 显示昵称
	 * 
	 * @param attackerName
	 * @param defenderName
	 */
	private void displayNames(String attackerName, String defenderName) {
		// 显示挑战者和守擂者的昵称
		navboard.getAttackerBox().getComponent(1).setVisible(false);
		navboard.getDefenderBox().getComponent(1).setVisible(false);

		JLabel attackLabel = new JLabel(attackerName);
		attackLabel.setForeground(Color.blue);
		navboard.getAttackerBox().add(attackLabel);
		JLabel defendLabel = new JLabel(defenderName);
		defendLabel.setForeground(Color.blue);
		navboard.getDefenderBox().add(defendLabel);

		navboard.getAttackerBox().validate();
		navboard.getDefenderBox().validate();
	}

	/**
	 * 结束游戏
	 */
	public void end() {
		winner = checkers.get(status);
		changeStatus(winner.getNickName() + "得胜", new Font(Font.SANS_SERIF,
				Font.BOLD, 16));
		// 移除监听器
		MouseListener listener = checkerboard.getListener();
		checkerboard.getCanvas().removeMouseListener(listener);
		// checkerboard.getCanvas().repaint();
	}

	public Checkerboard getCheckerboard() {
		return this.checkerboard;
	}

	public Judge getJudge() {
		return this.judge;
	}

	public Checker getAttacker() {
		return this.checkers.get("black");
	}

	public Checker getDefender() {
		return this.checkers.get("white");
	}

}
