package com.perry.http.view;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.perry.http.R;
import com.perry.http.view.LoadingView;


public class IssLoadingDialog extends Dialog {
	private Activity mActivity;
	private TextView loadingText;
	private LoadingView loading_progress;
	private View btn_cancel;
	private View btn_cancel_view;

	public IssLoadingDialog(Activity context) {
		super(context, R.style.dialog_normal);
		mActivity = context;
		setContentView(R.layout.loading);
		setProperty();
		setCancelable(false);
		loadingText = (TextView) findViewById(R.id.loading_text);
		loading_progress = (LoadingView) findViewById(R.id.loading_progress);
		btn_cancel = (View) findViewById(R.id.btn_cancel);
		btn_cancel_view = findViewById(R.id.btn_cancel_view);
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cancel();
			}

		});
	}

	public void setLoadingMessage(String message) {
		if (!TextUtils.isEmpty(message)) {
			loadingText.setText(message);
		}
	}

	public void setBtn_cancel(boolean isCancel){
		if(isCancel){
			btn_cancel.setVisibility(View.GONE);
			btn_cancel_view.setVisibility(View.INVISIBLE);
		}else {
			btn_cancel.setVisibility(View.VISIBLE);
			btn_cancel_view.setVisibility(View.VISIBLE);
		}
	}

	public void show() {
		try {
			super.show();
			loading_progress.start();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public void show(String message) {
		if (!TextUtils.isEmpty(message)) {
			loadingText.setText(message);
		}
		super.show();
	}

	@Override
	public void dismiss() {
		try {
			super.dismiss();
			loading_progress.stop();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	private void close() {
		if (!mActivity.isFinishing()) {
			mActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (isShowing()) {
						loading_progress.stop();
					}
				}
			});
		}
	}

	public void cancel() {
		loading_progress.stop();
		super.cancel();
	}

	private void setProperty() {
		Window window = getWindow();
		WindowManager.LayoutParams p = window.getAttributes();
		Display d = getWindow().getWindowManager().getDefaultDisplay();

		p.height = (int) (d.getHeight() * 1);
		p.width = (int) (d.getWidth() * 1);
		window.setAttributes(p);
	}

}
