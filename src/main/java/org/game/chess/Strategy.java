package org.game.chess;

import java.util.*;

/**
 * 步法
 */
public class Strategy {
	private List<Axis> history = new ArrayList<Axis>();

	public List<Axis> getHistory() {
		return this.history;
	}

	public boolean contains(Axis area) {
		return this.history.contains(area);
	}
}
