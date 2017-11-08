package com.perry.http.Listener;

/**
 * Created by isoftstone on 16/3/11.
 */
public interface ProgressCallback<T>  extends  AppCallback<T>{

          void  onProgress(int p, int total);
}
