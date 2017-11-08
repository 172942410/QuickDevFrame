package com.wifi12306.bean;

import android.content.ContentValues;
import android.database.Cursor;

import com.perry.bean.BaseBean;

import org.json.JSONObject;

/**
 * 登录
 */

public class LoginBean extends BaseBean{


    /**
     * statusId : 0
     * msg : 操作成功
     * data : {"staffId":"904","organizationName":"上海售服组1","staffName":"上海列车配送人员1","roleType":"2#","organizationId":"83614"}
     */

    public int statusId;
    public String msg;
    public LoginDataBean data;

    @Override
    public String toString() {
        return "LoginBean{" +
                "statusId=" + statusId +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class LoginDataBean {
        /**
         * staffId : 904                    登录用户ID
         * organizationName : 上海售服组1    所属组织机构名
         * staffName : 上海列车配送人员1     用户名
         * roleType : 2#                   角色列表   1 商家  2 列车配送员  按#隔开
         * organizationId : 83614          所属组织机构ID
         */

        public String staffId;
        public String organizationName;
        public String staffName;
        public String roleType;
        public String organizationId;

        @Override
        public String toString() {
            return "LoginDataBean{" +
                    "staffId='" + staffId + '\'' +
                    ", organizationName='" + organizationName + '\'' +
                    ", staffName='" + staffName + '\'' +
                    ", roleType='" + roleType + '\'' +
                    ", organizationId='" + organizationId + '\'' +
                    '}';
        }
    }

    @Override
    public Object parseJSON(JSONObject jsonObj) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public Object cursorToBean(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues beanToValues() {
        return null;
    }
}
