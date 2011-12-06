package org.game.chess;

import java.awt.*;

import javax.swing.*;

/**
 * 侧栏面板
 * 
 * @author
 * @version
 */
public class Navboard extends JPanel {

	private Box attackerBox;
	private Box defenderBox;
	private JButton startButton;
	private JLabel status;

	/**
	 * 布局
	 */
	public Navboard() {
		init();
		Box boxLayout = Box.createVerticalBox();
		boxLayout.add(attackerBox);
		boxLayout.add(defenderBox);
		boxLayout.add(startButton);
		boxLayout.add(Box.createVerticalStrut(340));
		boxLayout.add(status);

		add(boxLayout);
		setSize(300, 470);

	}

	/**
	 * 初始化
	 */
	private void init() {
		attackerBox = Box.createHorizontalBox();
		attackerBox.add(new JLabel("挑战者 "));
		attackerBox.add(new JTextField(10));

		defenderBox = Box.createHorizontalBox();
		defenderBox.add(new JLabel("守擂者 "));
		defenderBox.add(new JTextField(10));

		startButton = new JButton("开始");
		status = new JLabel("休战期", SwingConstants.LEFT);
	}

	public Box getAttackerBox() {
		return this.attackerBox;
	}

	public Box getDefenderBox() {
		return this.defenderBox;
	}

	public JButton getStartButton() {
		return this.startButton;
	}

	public JLabel getStatus() {
		return this.status;
	}

}
