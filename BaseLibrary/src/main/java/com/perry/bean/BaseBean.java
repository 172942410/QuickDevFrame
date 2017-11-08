
package com.perry.bean;

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONObject;

import java.io.Serializable;

public abstract class BaseBean<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**sqlite 中代表true的数值**/
    public static final int sqlite_true = 1;
    /**sqlite 中代表false的数值**/
    public static final int sqlite_false = 0;

    /**
     * 将json对象转化为Bean实例
     * 
     * @param jsonObj
     * @return
     */
    public abstract T parseJSON(JSONObject jsonObj);

    /**
     * 将Bean实例转化为json对象
     * 
     * @return
     */
    public abstract JSONObject toJSON();

    /**
     * 将数据库的cursor转化为Bean实例（如果对象涉及在数据库存取，需实现此方法）
     * 
     * @param cursor
     * @return
     */
    public abstract T cursorToBean(Cursor cursor);

    /**
     * 将Bean实例转化为一个ContentValues实例，供存入数据库使用（如果对象涉及在数据库存取，需实现此方法）
     * 
     * @return
     */
    public abstract ContentValues beanToValues();
}
