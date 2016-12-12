package com.test.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduled {

	public static void main(String[] args) {
	    Runnable runnable = new Runnable() {
	      public void run() {
	        // task to run goes here
	        System.out.println("Hello !!");
	      }
	    };
	    ScheduledExecutorService service = Executors
	                    .newSingleThreadScheduledExecutor();
	    service.scheduleAtFixedRate(runnable, 3, 2, TimeUnit.SECONDS);
	  }
}
