package com.cqu.srtp.common;

import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

@SuppressLint("HandlerLeak")
public class MainService extends Service implements Runnable {
	//广播接收器，接收网络变化
	public static NetUtil netReceiver=null;
	private static final String TAG="MainService";
	//当前的任务队列
	private static LinkedList<Task> tasks=new LinkedList<Task>();
	//用于全局的调用
	public static MainService mainService=null;
	//线程处理的标志信息
	public static boolean isRun=true;
	//当前的任务
	private static Task nowTask=null;
	
	public MainService() {
		mainService=this;
		isRun=true;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mainService=this;
		Log.i(TAG,"mainService is create");
		Thread t=new Thread(mainService);
		t.start();
		netReceiver=new NetUtil();
		//添加网络状态变化的广播接收器
		this.registerReceiver(netReceiver, 
				new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
		
	}
	
	//用于刷新UI界面，根据任务的id号
	private Handler hander=new Handler(){
		public void handleMessage(android.os.Message msg) {
			Log.i(TAG, "message id is "+msg.what);
			Log.i(TAG, "nowTask id is "+nowTask);
			Task t=(Task) msg.obj;
			if(msg.what==1){
				t.refresh();
			}
		};
	};

	
	//开启线程处理任务,根据任务的id号
	@Override
	public void run() {
		Log.i(TAG,"thread is running");
		while(isRun){
			//定期查询任务队列的任务数，如果有任务，就处理该任务
			//Log.i(TAG,"now, amount of activites is "+activities.size());
			if(tasks.size()!=0){
				Thread t=new DoTaskThread(tasks.removeFirst());
				t.start();
			}else{
				//若当前没有任务，则线程休息一定时间在执行
				try {
					Thread.sleep(1*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class DoTaskThread extends Thread{
		Task t;
		
		public DoTaskThread(Task t) {
			super();
			this.t = t;
		}

		@Override
		public void run() {
			Message msg=new Message();
			try{
				//处理该任务
				msg=new Message();
				msg.what=1;
				msg.obj=t;
				Log.i(TAG, "task is running...");
				t.run();
				Log.i(TAG, "task is done");
				
			}catch (Exception e) {
				msg.what=0;
				e.printStackTrace();
				Log.e("MainService", "run exception:"+e.getMessage());
			}
			hander.sendMessage(msg);
		}
	}
	
	public static void addTask(Task t){
		tasks.add(t);
	}
	
	
	
	public static void quit(Activity ac){
		isRun=false;
		if(netReceiver!=null){
			mainService.unregisterReceiver(netReceiver);
		}
		netReceiver=null;
		ac.stopService(new Intent("com.example.service.MainService"));
		Log.i(TAG, "service "+mainService.getClass().getName()+" is closed");
	}
	 //提示是否退出应用程序
    public static void promptExitApp(final Activity context)
    {
       //创建对话框
    	AlertDialog.Builder ab=new AlertDialog.Builder(context);
    	
    	ab.setMessage("是否退出?");
    	ab.setPositiveButton("退出", 
    		new OnClickListener()
    	     {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss();
					quit(context);//退出整个应用
				}
    	     }
    	    );
    	ab.setNegativeButton("不退出", null);
    	ab.show();
    }
    
	//提示用户网络异常
	public static void alertNetError(final Activity context)
	{
	   	AlertDialog.Builder ab=new AlertDialog.Builder(context);
	   	//设定标题
	   	ab.setTitle("网络异常");
	  //设定标题
	   	ab.setMessage("请检查网络");
	   	//设定退出按钮
	   	ab.setNegativeButton("退出程序",
	     new OnClickListener()
	   	 {
  	       @Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
		       dialog.cancel(); 
		       quit(context);		
			}
	   	 }
	   	);
		//网络设置按钮
	   	ab.setPositiveButton("网络设置",
	   	     new OnClickListener()
	   	   	 {
	     	       @Override
	   			public void onClick(DialogInterface dialog, int which) {
	   				// TODO Auto-generated method stub
	   		        dialog.dismiss();
	     	    	context.startActivityForResult(
	     	         new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS), 0);	
	   			}
	   	   	 }
	   	   	);
	   	 ab.create().show();
	}
}
