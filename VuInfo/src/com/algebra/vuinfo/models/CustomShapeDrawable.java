package com.algebra.vuinfo.models;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

public class CustomShapeDrawable extends ShapeDrawable {
	private final Paint fillpaint, strokepaint;
	int strokeWidth;

	/**
	 * Draw shape with desired colors of the fill or border if stroke set true
	 * and the background.
	 * 
	 * @param s
	 *            shape to draw
	 * @param colorBkg
	 *            color to fill shape
	 * @param colorFill
	 *            color of border or inner rectangle
	 * @param stroke
	 *            true if with stroke or false for filled rectangle
	 * @param strokeWidth
	 *            border width
	 */
	public CustomShapeDrawable(Shape s, int colorBkg, int colorFill, boolean stroke,
			int strokeWidth) {
		super(s);
		fillpaint = new Paint(this.getPaint());
		fillpaint.setColor(colorBkg);
		strokepaint = new Paint(fillpaint);
		if (stroke) strokepaint.setStyle(Paint.Style.STROKE);
		strokepaint.setStrokeWidth(strokeWidth);
		this.strokeWidth = strokeWidth;
		strokepaint.setColor(colorFill);
	}

	@Override
	protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
		shape.resize(canvas.getClipBounds().right,
				canvas.getClipBounds().bottom);
		shape.draw(canvas, fillpaint);

		Matrix matrix = new Matrix();
		// Source
		RectF rectSrc = new RectF(0 - 12f, 0 - 12f,
				canvas.getClipBounds().right + 12f,
				canvas.getClipBounds().bottom + 12f);
		// Destination
		RectF rectDst = new RectF(strokeWidth / 2, strokeWidth / 2,
				canvas.getClipBounds().right - strokeWidth / 2,
				canvas.getClipBounds().bottom - strokeWidth / 2);
		// Map source to destination (transform our stroke shape to be slightly
		// smaller than the boundary)
		matrix.setRectToRect(rectSrc, rectDst, Matrix.ScaleToFit.FILL);

		canvas.concat(matrix);
		shape.draw(canvas, strokepaint);
	}
}
