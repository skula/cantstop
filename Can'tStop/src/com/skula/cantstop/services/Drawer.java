package com.skula.cantstop.services;

import java.util.List;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.cantstop.R;
import com.skula.cantstop.cnst.PictureLibrary;

public class Drawer {
	private static final int X0 = 25;
	private static final int Y0 = 850;
	private static final int DX = 70;
	private static final int DY = 70;
	private static final int PAWN_SIZE = 50;
	private static final int DICE_SIZE = 50;
	private static final Rect DICE_RECT = new Rect(0, 0, DICE_SIZE, DICE_SIZE);
	private PictureLibrary lib;
	private Paint paint;
	private GameEngine engine;

	public Drawer(Resources res, GameEngine engine) {
		this.engine = engine;
		this.paint = new Paint();
		this.lib = new PictureLibrary(res);
	}

	public void draw(Canvas c) {
		// c.drawBitmap(lib.get(R.drawable.background), new Rect(0, 0, 768,
		// 1024), new Rect(0, 0, 800, 1280), paint);
		paint.setColor(Color.WHITE);
		c.drawRect(new Rect(0, 0, 800, 1280), paint);
		paint.setTextSize(40f);
		drawColumns(c);
		drawPawns(c);
		// drawPlayersPositions(c);
		 drawChoices(c);
	}
	
	private void drawChoices(Canvas c){
		int x = 20;
		int y = 900;
		int dx1 = 10;
		int dx2 = 30;
		int dx3 = 50;
		
		int dices[] = engine.getDices();
		// dés 0-1,2-3
		c.drawBitmap(lib.get(getDiceDrawable(dices[0])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx1;
		c.drawBitmap(lib.get(getDiceDrawable(dices[1])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx2;
		c.drawBitmap(lib.get(getDiceDrawable(dices[2])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx1;
		c.drawBitmap(lib.get(getDiceDrawable(dices[3])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		// dés 1-2,3-0
		x+= DICE_SIZE + dx3;
		c.drawBitmap(lib.get(getDiceDrawable(dices[1])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx1;
		c.drawBitmap(lib.get(getDiceDrawable(dices[2])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx2;
		c.drawBitmap(lib.get(getDiceDrawable(dices[3])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx1;
		c.drawBitmap(lib.get(getDiceDrawable(dices[0])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		// dés 0-2,1-3
		x+= DICE_SIZE + dx3;
		c.drawBitmap(lib.get(getDiceDrawable(dices[0])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx1;
		c.drawBitmap(lib.get(getDiceDrawable(dices[2])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx2;
		c.drawBitmap(lib.get(getDiceDrawable(dices[1])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
		x+= DICE_SIZE + dx1;
		c.drawBitmap(lib.get(getDiceDrawable(dices[3])), DICE_RECT, new Rect(x, y, x + DICE_SIZE, y + DICE_SIZE), paint);
	}
	
	private int getDiceDrawable(int i){
		switch(i){
		case 1:
			return R.drawable.dice_1;
		case 2:
			return R.drawable.dice_2;
		case 3:
			return R.drawable.dice_3;
		case 4:
			return R.drawable.dice_4;
		case 5:
			return R.drawable.dice_5;
		case 6:
			return R.drawable.dice_6;
		default:
			return -1;
		}
	}

	private void drawColumns(Canvas c) {
		int x = X0;
		int y = Y0;
		drawColumn(c, x, y, 2, "2");
		x += DX;
		drawColumn(c, x, y, 4, "3");
		x += DX;
		drawColumn(c, x, y, 6, "4");
		x += DX;
		drawColumn(c, x, y, 8, "5");
		x += DX;
		drawColumn(c, x, y, 10, "6");
		x += DX;
		drawColumn(c, x, y, 12, "7");
		x += DX;
		drawColumn(c, x, y, 10, "8");
		x += DX;
		drawColumn(c, x, y, 8, "9");
		x += DX;
		drawColumn(c, x, y, 6, "10");
		x += DX;
		drawColumn(c, x, y, 4, "11");
		x += DX;
		drawColumn(c, x, y, 2, "12");
	}

	private void drawColumn(Canvas c, int x, int y, int n, String label) {
		for (int i = 0; i < n; i++) {
			paint.setColor(Color.YELLOW);
			c.drawRect(new Rect(x, y - (i * DY), x + PAWN_SIZE, y - (i * DY) + PAWN_SIZE), paint);
			paint.setColor(Color.BLACK);
			c.drawText(label, x + 5, y - (i * DY) + 40, paint);
		}
		paint.setColor(Color.RED);
		c.drawRect(new Rect(x, y - (n * DY), x + PAWN_SIZE, y - (n * DY) + PAWN_SIZE), paint);
		paint.setColor(Color.BLACK);
		c.drawText(label, x + 5, y - (n * DY) + 40, paint);
	}

	private void drawPawns(Canvas c) {
		Map<Integer, Integer> map = engine.getPawns();
		int x = 0;
		int y = 0;
		for (Integer i : map.keySet()) {
			x = X0 + (i - 2) * DX;
			y = Y0 - map.get(i) * DY;
			paint.setColor(Color.BLACK);
			c.drawRect(new Rect(x, y, x + PAWN_SIZE, y + PAWN_SIZE), paint);
		}
	}

	private void drawPlayersPositions(Canvas c) {
		int x = 0;
		int y = 0;
		List<Map<Integer, Integer>> l = engine.getPlayers();
		for (int i = 0; i < l.size(); i++) {
			Map<Integer, Integer> m = l.get(i);
			for (Integer c2 : m.keySet()) {
				int dy = getDyLevel(i, c2, m.get(c2));
				x = X0 + (i - 2) * DX;
				y = Y0 - m.get(i) * DY;
				paint.setColor(Color.BLACK);
				c.drawRect(new Rect(x, y, x + PAWN_SIZE, y + PAWN_SIZE), paint);
			}
		}
	}

	private int getDyLevel(int id, int col, int level) {
		int cpt = 0;
		List<Map<Integer, Integer>> l = engine.getPlayers();
		for (int i = 0; i < l.size(); i++) {
			if (i == id) {
				return cpt;
			}
			Map<Integer, Integer> m = l.get(i);
			for (Integer i2 : m.keySet()) {
				if (i2 == col && m.get(i2) == level) {
					cpt++;
				}
			}
		}
		return cpt;
	}
}
