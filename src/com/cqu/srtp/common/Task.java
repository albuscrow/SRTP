package com.cqu.srtp.common;

import android.app.Activity;

abstract public class Task {
	public static final int A=1;
	Object pa;
	public Activity ac=null;
	
	abstract public void run();
	abstract public void refresh();
}

