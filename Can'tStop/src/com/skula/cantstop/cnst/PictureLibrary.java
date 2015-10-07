package com.skula.cantstop.cnst;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skula.cantstop.R;

public class PictureLibrary{
	private Map<Integer, Bitmap> map;

	@SuppressLint("UseSparseArrays")
	public PictureLibrary(Resources res) {
		this.map = new HashMap<Integer, Bitmap>();
		this.map.put(R.drawable.dice_1, BitmapFactory.decodeResource(res, R.drawable.dice_1));
		this.map.put(R.drawable.dice_2, BitmapFactory.decodeResource(res, R.drawable.dice_2));
		this.map.put(R.drawable.dice_3, BitmapFactory.decodeResource(res, R.drawable.dice_3));
		this.map.put(R.drawable.dice_4, BitmapFactory.decodeResource(res, R.drawable.dice_4));
		this.map.put(R.drawable.dice_5, BitmapFactory.decodeResource(res, R.drawable.dice_5));
		this.map.put(R.drawable.dice_6, BitmapFactory.decodeResource(res, R.drawable.dice_6));
	}

	public Bitmap get(int id) {
		return map.get(id);
	}
}
