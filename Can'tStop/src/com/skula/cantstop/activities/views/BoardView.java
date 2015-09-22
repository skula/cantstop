package com.skula.cantstop.activities.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {
	private Paint paint;
	private Resources res;

	public BoardView(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		invalidate();
		return true;
	}

	@Override
	public void draw(Canvas canvas) {

	}
}
