package com.skula.cantstop.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameEngine {
	private static final int NULL_COMBINATION = -1;
	private static final int COMBINATIONS_PARTS_COUNT = 3;
	private static final int COMBINATIONS_COUNT = 3;
	private static final int DICES_COUNT = 4;
	private static final int COLUMNS_COUNT = 12;
	private static final int SCORE_MAX = 3;
	private static final int COMBINATION_OR = 0;
	private static final int COMBINATION_AND = 1;

	private int nPlayers;
	private int scores[];
	private int token;

	private Map<Integer, Integer> pawns;
	private int dices[];
	private int combinatons[][];
	private List<Map<Integer, Integer>> players;
	private List<Integer> colFinished;

	public GameEngine(int nPlayers) {
		this.nPlayers = nPlayers;
		this.scores = new int[nPlayers];

		this.token = 0;
		this.pawns = new HashMap<Integer, Integer>();

		this.players = new ArrayList<Map<Integer, Integer>>();
		for (int i = 0; i < nPlayers; i++) {
			Map<Integer, Integer> tmp = new HashMap<Integer, Integer>();
			for (int j = 2; j <= COLUMNS_COUNT; j++) {
				tmp.put(j, 0);
			}
			players.add(tmp);
		}

		this.dices = new int[DICES_COUNT];

		this.combinatons = new int[COMBINATIONS_COUNT][COMBINATIONS_PARTS_COUNT];
		for (int i = 0; i < 3; i++) {
			this.combinatons[i][0] = NULL_COMBINATION;
			this.combinatons[i][1] = NULL_COMBINATION;
			this.combinatons[i][2] = NULL_COMBINATION;
		}

		this.colFinished = new ArrayList<Integer>();

		// bouchon
		pawns.put(7, 5);
		pawns.put(6, 5);
		// addLevel(0, 2);
		// addLevel(0, 2);
		colFinished.add(5);
	}

	public boolean isWinner(int playerId) {
		return scores[playerId] == SCORE_MAX;
	}

	public void nextPlayer() {
		token = token == 0 ? 1 : 0;
	}

	public void combineDices() {
		int pawnsLeft = 3 - pawns.size();

		int a1 = dices[0] + dices[1];
		combinatons[0][0] = (pawns.containsKey(a1) || pawnsLeft > 0) && !colFinished.contains(a1) ? a1
				: NULL_COMBINATION;
		int a2 = dices[2] + dices[3];
		combinatons[0][1] = (pawns.containsKey(a2) || pawnsLeft > 0) && !colFinished.contains(a2) ? a2
				: NULL_COMBINATION;
		if (pawnsLeft == 2 || (pawns.containsKey(a1) && pawns.containsKey(a2))
				|| (pawnsLeft == 1 && pawns.containsKey(a1)) || (pawnsLeft == 1 && pawns.containsKey(a2))) {
			combinatons[0][2] = COMBINATION_AND;
		} else {
			combinatons[0][2] = COMBINATION_OR;
		}

		int b1 = dices[1] + dices[2];
		combinatons[1][0] = (pawns.containsKey(b1) || pawnsLeft > 0) && !colFinished.contains(b1) ? b1
				: NULL_COMBINATION;
		int b2 = dices[3] + dices[0];
		combinatons[1][1] = (pawns.containsKey(b2) || pawnsLeft > 0) && !colFinished.contains(b2) ? b2
				: NULL_COMBINATION;
		if (pawnsLeft == 2 || (pawns.containsKey(b1) && pawns.containsKey(b2))
				|| (pawnsLeft == 1 && pawns.containsKey(b1)) || (pawnsLeft == 1 && pawns.containsKey(b2))) {
			combinatons[1][2] = COMBINATION_AND;
		} else {
			combinatons[1][2] = COMBINATION_OR;
		}

		int c1 = dices[0] + dices[2];
		combinatons[2][0] = (pawns.containsKey(c1) || pawnsLeft > 0) && !colFinished.contains(c1) ? c1
				: NULL_COMBINATION;
		int c2 = dices[1] + dices[3];
		combinatons[2][1] = (pawns.containsKey(c2) || pawnsLeft > 0) && !colFinished.contains(c2) ? c2
				: NULL_COMBINATION;
		if (pawnsLeft == 2 || (pawns.containsKey(c1) && pawns.containsKey(c2))
				|| (pawnsLeft == 1 && pawns.containsKey(c1)) || (pawnsLeft == 1 && pawns.containsKey(c2))) {
			combinatons[2][2] = COMBINATION_AND;
		} else {
			combinatons[2][2] = COMBINATION_OR;
		}

	}

	public void rollTheDice() {
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			dices[i] = r.nextInt(6) + 1;
		}
	}

	public boolean canContinu() {
		for (int i = 0; i < 3; i++) {
			if (combinatons[i][0] != NULL_COMBINATION || combinatons[i][1] != NULL_COMBINATION) {
				return true;
			}
		}
		return false;
	}

	public void updatePawns() {
		this.pawns = new HashMap<Integer, Integer>();
	}

	public void save(int playerId) {
		for (Integer key : pawns.keySet()) {
			players.get(playerId).put(key, pawns.get(key));
			switch (key) {
			case 2:
				if (pawns.get(key) >= 3) {
					colFinished.add(2);
					scores[playerId]++;
				}
				break;
			case 3:
				if (pawns.get(key) >= 5) {
					colFinished.add(3);
					scores[playerId]++;
				}
				break;
			case 4:
				if (pawns.get(key) >= 7) {
					colFinished.add(4);
					scores[playerId]++;
				}
				break;
			case 5:
				if (pawns.get(key) >= 9) {
					colFinished.add(5);
					scores[playerId]++;
				}
				break;
			case 6:
				if (pawns.get(key) >= 11) {
					colFinished.add(6);
					scores[playerId]++;
				}
				break;
			case 7:
				if (pawns.get(key) >= 13) {
					colFinished.add(7);
					scores[playerId]++;
				}
				break;
			case 8:
				if (pawns.get(key) >= 11) {
					colFinished.add(8);
					scores[playerId]++;
				}
				break;
			case 9:
				if (pawns.get(key) >= 9) {
					colFinished.add(9);
					scores[playerId]++;
				}
				break;
			case 10:
				if (pawns.get(key) >= 7) {
					colFinished.add(10);
					scores[playerId]++;
				}
				break;
			case 11:
				if (pawns.get(key) >= 5) {
					colFinished.add(11);
					scores[playerId]++;
				}
				break;
			case 12:
				if (pawns.get(key) >= 3) {
					colFinished.add(12);
					scores[playerId]++;
				}
				break;
			default:
				break;
			}
		}

		updatePawns();
	}

	public void executeCombination(String combination) {
		if (combination.contains(" et ")) {
			String tmp[] = combination.split(" et ");
			addLevel(token, Integer.valueOf(tmp[0]));
			addLevel(token, Integer.valueOf(tmp[1]));
		} else {
			addLevel(token, Integer.valueOf(combination));
		}
	}

	private void addLevel(int playerId, int column) {
		int tmp = 0;
		if (pawns.containsKey(column)) {
			tmp = pawns.get(column) + 1;
		} else {
			tmp = players.get(playerId).get(column) + 1;
		}

		pawns.put(column, tmp);
		switch (column) {
		case 2:
			if (tmp >= 3) {
				pawns.put(2, 3);
			}
			break;
		case 3:
			if (tmp >= 5) {
				pawns.put(3, 5);
			}
			break;
		case 4:
			if (tmp >= 7) {
				pawns.put(4, 7);
			}
			break;
		case 5:
			if (tmp >= 9) {
				pawns.put(5, 9);
			}
			break;
		case 6:
			if (tmp >= 11) {
				pawns.put(6, 11);
			}
			break;
		case 7:
			if (tmp >= 13) {
				pawns.put(7, 13);
			}
			break;
		case 8:
			if (tmp >= 11) {
				pawns.put(8, 11);
			}
			break;
		case 9:
			if (tmp >= 9) {
				pawns.put(9, 9);
			}
			break;
		case 10:
			if (tmp >= 7) {
				pawns.put(10, 7);
			}
			break;
		case 11:
			if (tmp >= 5) {
				pawns.put(11, 5);
			}
			break;
		case 12:
			if (tmp >= 3) {
				pawns.put(12, 3);
			}
			break;
		default:
			break;
		}
	}

	public int getLevel(int playerId, int column) {
		return players.get(playerId).get(column);
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public Map<Integer, Integer> getPawns() {
		return pawns;
	}

	public void setPawns(Map<Integer, Integer> pawns) {
		this.pawns = pawns;
	}

	public int[] getDices() {
		return dices;
	}

	public void setDices(int[] dices) {
		this.dices = dices;
	}

	public int[][] getCombinatons() {
		return combinatons;
	}

	public void setCombinatons(int[][] combinatons) {
		this.combinatons = combinatons;
	}

	public List<Map<Integer, Integer>> getPlayers() {
		return players;
	}

	public void setPlayers(List<Map<Integer, Integer>> players) {
		this.players = players;
	}
}
