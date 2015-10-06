package com.skula.cantstop.cnst;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;

public class PictureLibrary{
	private Map<Integer, Bitmap> map;

	@SuppressLint("UseSparseArrays")
	public PictureLibrary(Resources res) {
		this.map = new HashMap<Integer, Bitmap>();
	}

	public Bitmap get(int id) {
		return map.get(id);
	}
}
