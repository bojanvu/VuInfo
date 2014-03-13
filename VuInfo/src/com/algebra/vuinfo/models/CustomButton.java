package com.algebra.vuinfo.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.Button;

public class CustomButton extends Button {
	private int[] colorRGB = { 60, 180, 255 };// 55, 185, 255 };// 64, 191, 255
												// };// 51, 192, 255};
	private int[] colorRGB2 = { 150, 150, 150 };
	private int color30, color30B, color80, color127, color191, color191G;

	public CustomButton(Context context) {
		super(context);

		// Colors with various alpha values
		color127 = Color.argb(127, colorRGB2[0], colorRGB2[1], colorRGB2[2]);
		color30 = Color.argb(30, colorRGB2[0], colorRGB2[1], colorRGB2[2]);
		color80 = Color.argb(80, colorRGB[0], colorRGB[1], colorRGB[2]);
		color191 = Color.argb(191, colorRGB[0], colorRGB[1], colorRGB[2]);
		color191G = Color.argb(225, colorRGB2[0], colorRGB2[1], colorRGB2[2]);

		// radius for round corners
		float radi = 12;
		float[] radii = new float[] { radi, radi, radi, radi, radi, radi, radi,
				radi };
		float[] radii2 = new float[] { radi * 2, radi * 2, radi * 2, radi * 2,
				radi * 2, radi * 2, radi * 2, radi * 2 };

		// for insets to add(outside of bounds - if negative value) or to
		// subtract(if positive) from rectangle sides
		RectF inset = new RectF(10, 10, 10, 10);
		// Round corners rectangle (without padding) with inner transparent area
		// (rectangle), or to better say the frame.
		RoundRectShape frameRect = new RoundRectShape(radii2, inset, radii);
		// Custom rectangle, because of paddings doesn't work for list
		// items(drawable)
		RoundRectShape rect = new RoundRectShape(radii, null, null);
		CustomShapeDrawable bgCustom = new CustomShapeDrawable(rect,
				Color.TRANSPARENT, color127, false, 0);

		CustomShapeDrawable bgSelected = new CustomShapeDrawable(rect, color80,
				color191, false, 0);// color105

		// shape for 1st layer
		CustomShapeDrawable layer1 = new CustomShapeDrawable(rect,
				Color.TRANSPARENT, color30, false, 0);
		// set color
		layer1.getPaint().setColor(color30);

		// shape for 2nd layer
		CustomShapeDrawable layer2 = new CustomShapeDrawable(rect,
				Color.TRANSPARENT, color127, true, 1);
		layer2.getPaint().setColor(color191G);

		// put layers together in one drawable with layers
		Drawable[] layers = new Drawable[2];
		layers[0] = layer1;
		layers[1] = layer2;
		LayerDrawable layerDrawable = new LayerDrawable(layers);
		// get one drawable because layers doesn't works (transparency problem)
		Drawable bgEnable = geSingleDrawable(layerDrawable);

		// same for focused state
		layer1 = new CustomShapeDrawable(rect, Color.TRANSPARENT, color80,
				false, 0);
		layer1.getPaint().setColor(color80);
		layer2 = new CustomShapeDrawable(rect, Color.TRANSPARENT, color191,
				true, 5);
		layer2.getPaint().setColor(color191);
		Drawable[] layers2 = new Drawable[2];
		layers2[0] = layer1;
		layers2[1] = layer2;
		layerDrawable = new LayerDrawable(layers2);
		Drawable bgFocused = geSingleDrawable(layerDrawable);

		// add drawables for states
		final StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[] { android.R.attr.state_pressed },
				bgSelected);
		stateListDrawable.addState(new int[] { android.R.attr.state_focused },
				bgFocused);
		stateListDrawable.addState(new int[] { -android.R.attr.state_enabled },
				bgEnable);
		stateListDrawable.addState(new int[] {}, bgCustom); // normal state

		setBackgroundDrawable(stateListDrawable);

	}

	/**
	 * Sets RGB color for the button for example colorRGB = {0,0,0}, range from
	 * 0 to 250.
	 **/
	public void setColor(int[] colorRGB) {
		this.colorRGB = colorRGB;
	}

	/**
	 * Method takes layer drawable and returns drawable.
	 * 
	 * @param layerDrawable
	 *            layers that will be returned as a single drawable.
	 * 
	 **/
	public Drawable geSingleDrawable(LayerDrawable layerDrawable) {

		int widthInPixels = (int) (getResources().getDisplayMetrics().densityDpi);
		int heightInPixels = (int) ((getResources().getDisplayMetrics().densityDpi) / 3.3);
		// insets if needed
		int insetLeft = 0, insetTop = 0, insetRight = 0, insetBottom = 0;
		layerDrawable.setLayerInset(1, insetLeft, insetTop, insetRight,
				insetBottom);

		Bitmap bitmap = Bitmap.createBitmap(widthInPixels, heightInPixels,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		layerDrawable.setBounds(0, 0, widthInPixels, heightInPixels);
		layerDrawable.draw(canvas);

		BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),
				bitmap);
		bitmapDrawable.setBounds(0, 0, widthInPixels, heightInPixels);

		return bitmapDrawable;
	}
}
