package com.wifi12306.bean.plugin;

import android.content.ContentValues;
import android.database.Cursor;

import com.perry.bean.BaseBean;

import org.json.JSONObject;

/**
 * 个人详情bean
 * @author shidongxue
 *
 */
public class DetailDataBean extends BaseBean<DetailDataBean> {
	private static final long serialVersionUID = 1L;
	
	public String id; // user id
	public String username; // 姓名
	public String login_email; //  新增 工号
	public String phone_no; // 个人电话
	public String tel_no; // 公司电话
	public String signature; // 签名
//	public ArrayList<String> gsnames; // 公司
	public String gsname;
//	public ArrayList<String> bmnames; // 部门
	public String bmname;
//	public ArrayList<String> postnames; // 职务
	public String postname;
	public String imgUrl;
	public String smallImageUrl;
	public String code;
	public String sex;// 性别
	public String email;// 邮箱
	public String remark;// 备注

	@Override
	public DetailDataBean parseJSON(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailDataBean cursorToBean(Cursor cursor) {
		return this;
	}

	@Override
	public ContentValues beanToValues() {
		ContentValues values = new ContentValues();

		return values;
	}

	@Override
	public String toString() {
		return "DetailDataBean{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", login_email='" + login_email + '\'' +
				", phone_no='" + phone_no + '\'' +
				", tel_no='" + tel_no + '\'' +
				", signature='" + signature + '\'' +
				", gsname='" + gsname + '\'' +
				", bmname='" + bmname + '\'' +
				", postname='" + postname + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				", smallImageUrl='" + smallImageUrl + '\'' +
				", code='" + code + '\'' +
				", sex='" + sex + '\'' +
				", email='" + email + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}
