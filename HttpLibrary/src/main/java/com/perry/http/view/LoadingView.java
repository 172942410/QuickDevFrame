package com.perry.http.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import com.perry.http.R;

public class LoadingView extends View {

	Bitmap bitmap1;
	Bitmap bitmap2;
	Bitmap bitmap3;
	protected boolean isStop = true;
	private int degree = 0;
	private long TIME_STEP = 300;

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		bitmap1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_login_jindu_1st);
		bitmap2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_login_jindu_2nd);
		bitmap3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.icon_login_jindu_3rd);
	}

	private void drawRoundBitmap(Canvas canvas) {
		RectF rf = new RectF();
		rf.left = 0;
		rf.right = getWidth();
		rf.top = 0;
		rf.bottom = getHeight();
		switch (degree) {
		case 0:
			canvas.drawBitmap(bitmap1, null, rf, null);
			break;
		case 1:
			canvas.drawBitmap(bitmap2, null, rf, null);
			break;
		case 2:
			canvas.drawBitmap(bitmap3, null, rf, null);
			break;

		default:
			break;
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			new Thread() {
				public void run() {
					while (!isStop) {
						if (isStop) {
							return;
						}
						SystemClock.sleep(TIME_STEP);
						degree += 1;
						if (degree >= 3) {
							degree = 0;
						}
						postInvalidate();
					}
				}

			}.start();

		}
	};

	public void start() {
		isStop = false;
		handler.sendMessage(handler.obtainMessage());
	}

	public void stop() {
		isStop = true;
	}
	
	@Override
	protected void onDetachedFromWindow() {
		isStop = true;
		super.onDetachedFromWindow();
	}

	protected void onDraw(Canvas canvas) {
		drawRoundBitmap(canvas);
		super.onDraw(canvas);
	};

}
