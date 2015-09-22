package com.skula.cantstop.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine{
	private int nPlayers;
	private int blackPawns[];
	private List<Map<Integer, Integer>> pawns;
	
	public GameEngine(int nPlayers){
		this.nPlayers = nPlayers;
		this.blackPawns = new int[3];
		for(int i=0; i<3; i++){
			blackPawns[i] = 0;
		}
		
		this.pawns = new ArrayList<Map<Integer, Integer>>();
		for(int i=0; i<nPlayers; i++){
			Map tmp = new HashMap<Integer, Integer>();
			for(int j=0; j<11; j++){
				tmp.put(j, 0);
			}
			pawns.add(tmp);
		}
	}
	
	public boolean canContinu(){
		return true;
	}
	
	public int getRank(int playerId, int column){
		return pawns.get(playerId).get(column);
	}
}