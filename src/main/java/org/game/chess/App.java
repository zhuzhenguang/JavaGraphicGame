package org.game.chess;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * 主程序
 *
 */
public class App extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * 内容面板
	 */
	private Checkerboard checkerboard;

	private Navboard navboard;

	/**
	 * 构造函数进行初始化
	 */
	public App() {
		super("快乐三子棋");

		// 初始化棋盘
		checkerboard = new Checkerboard();
		navboard = new Navboard();

		// 设置大小
		setSize(770, 470);
		// 进行布局
		Container contentContainer = this.getContentPane();

		// 将棋盘纳入其中
		Box boxlayout = Box.createHorizontalBox();
		boxlayout.add(checkerboard);
		boxlayout.add(navboard);
		contentContainer.add(boxlayout);

	}

	public static void main(String[] args) {
		// System.out.println( "Hello World!" );
		try {
			// 主程序用一个线程启动
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// 设置外观
					/*
					 * LookAndFeel laf = new
					 * SubstanceBusinessBlackSteelLookAndFeel(); try {
					 * UIManager.setLookAndFeel(laf); } catch
					 * (UnsupportedLookAndFeelException e) {
					 * e.printStackTrace(); }
					 * SubstanceLookAndFeel.setToUseConstantThemesOnDialogs
					 * (true); UIManager.put(SubstanceLookAndFeel.
					 * TABBED_PANE_CLOSE_BUTTONS_PROPERTY, Boolean.TRUE);
					 * UIManager.put(SubstanceLookAndFeel.SHOW_EXTRA_WIDGETS,
					 * Boolean.TRUE);
					 *
					 * JFrame.setDefaultLookAndFeelDecorated(true);
					 * JDialog.setDefaultLookAndFeelDecorated(true);
					 */

					// 初始化一个JFrame
					App app = new App();

					// 添加关闭方法
					app.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});

					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					Dimension frameSize = app.getSize();
					// 设置初始位置为屏幕中间
					if (frameSize.height > screenSize.height) {
						frameSize.height = screenSize.height;
					}
					if (frameSize.width > screenSize.width) {
						frameSize.width = screenSize.width;
					}
					app.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					app.setVisible(true);
					// app.pack();

					Game game = new Game(app.checkerboard, app.navboard);
					game.init();
				}
			});
		} catch (UnsupportedClassVersionError e) {
			// 版本异常提示
			String vers = System.getProperty("java.version");
			if (vers.compareTo("1.6.0") < 0) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error : 程序必须运行在1.6.0或更高版本的JVM上", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}

}
