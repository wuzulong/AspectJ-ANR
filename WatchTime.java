package com.example.aspectjdemo;  
  
import java.lang;
import android.app.Application;
import android.content.Context;

import com.marno.gameclient.utils.MLog;
import com.marno.gameclient.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
  
/** 
 * Created by wuzulong on 2017/6/30.
 */
 
public class WatchTime {
	
  private long startTime;//开始时间
  private long endTime;//结束时间
  private long elapsedTime;//运行时间
  
  public WatchTime() {}
  
  private void reset() {//初始化
    startTime = 0;
    endTime = 0;
    elapsedTime = 0;
  }
  
  public void start() {//记录开始
    reset();
    startTime = System.nanoTime();
  }
  
  public void stop() {//记录结束时间,并计算运行时间
    if (startTime != 0) {
      endTime = System.nanoTime();
      elapsedTime = endTime - startTime;
    } else {
      reset();
    }
  }
  
  public long getTotalTimeMillis() {
    return (elapsedTime != 0) ? TimeUnit.NANOSECONDS.toMillis(endTime - startTime) : 0;
  }
  
}
