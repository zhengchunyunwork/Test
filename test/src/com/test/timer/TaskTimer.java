package com.test.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TaskTimer {

	  public static void main(String[] args) throws Exception {
		    TimerTask task = new TimerTask() {
		      @Override
		      public void run() {
		        // task to run goes here
		        System.out.println("Hello !!!");
		      }
		    };
		    Timer timer = new Timer();
//		    long delay = 0;
//		    long intevalPeriod = 3 * 1000;
//		    timer.scheduleAtFixedRate(task, delay,intevalPeriod);
		    
		    //延迟一天
		    long daySpan = 24 * 60 * 60 * 1000;
		    // 规定的每天时间15:33:30运行
		    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '16:36:30'");
		    // 首次运行时间
		    Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
		    
		    if(System.currentTimeMillis() > startTime.getTime())
		    	  startTime = new Date(startTime.getTime() + daySpan);
		    
		    System.out.println(startTime);
		    timer.scheduleAtFixedRate(task, startTime, daySpan);
		  } 
}
