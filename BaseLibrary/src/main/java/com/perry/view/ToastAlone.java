package com.perry.view;

import android.content.Context;
import android.widget.Toast;

/**
 * 只会显示一个toast </br>
 * 不会有延迟的toast
 * @author pengjun
 *
 */
public class ToastAlone extends Toast {
	Context context;
	/**
	 * 唯一的toast
	 */
	private static Toast mToast = null;
	
	public ToastAlone(Context context) {
		super(context);
		this.context=context;
	}

	/**
	 * 显示的可以及时清除
	 * @param ctx
	 * @param tips
	 * @param lastTime
	 * @return
	 */
	public static Toast showToast(Context ctx, int stringid, int lastTime) {
		if (mToast != null) {
//			mToast.cancel();
		} else {
			mToast = Toast.makeText(ctx, stringid, lastTime);
		}
		mToast.setText(stringid);
		mToast.show();
		return mToast;
	}

	/**
	 * 显示的可以及时清除
	 * @param ctx
	 * @param tips
	 * @param lastTime
	 * @return
	 */
	public static Toast showToast(Context ctx, String tips, int lastTime) {
		if (mToast != null) {
//			mToast.cancel();
		} else {
			mToast = Toast.makeText(ctx, tips, lastTime);
		}
		mToast.setText(tips);
		mToast.show();
		return mToast;
	}

}
