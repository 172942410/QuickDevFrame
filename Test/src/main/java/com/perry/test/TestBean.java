package com.perry.test;

import java.io.Serializable;

/**
 * Created by lipengjun on 2016/11/29.
 */

public class TestBean<T> implements Serializable{
    public int statusId;
    public String msg;
    public T data;
}
