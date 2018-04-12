package com.wifi12306.bean.plugin;

import android.content.ContentValues;
import android.database.Cursor;

import com.perry.bean.BaseBean;

import org.json.JSONObject;

/**
 *
 * @author lipengjun
 *
 */
public class SucDetailDataBean extends BaseBean<SucDetailDataBean> {
	private static final long serialVersionUID = 1L;
	public int result_code;
	public String result_msg;
	public DetailDataBean user;
	@Override
	public SucDetailDataBean parseJSON(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SucDetailDataBean cursorToBean(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentValues beanToValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "SucDetailDataBean{" +
				"result_code=" + result_code +
				", result_msg='" + result_msg + '\'' +
				", user=" + user +
				'}';
	}
}
