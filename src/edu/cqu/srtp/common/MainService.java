package edu.cqu.srtp.common;

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
	//�㲥����������������仯
	public static NetUtil netReceiver=null;
	private static final String TAG="MainService";
	//��ǰ���������
	private static LinkedList<Task> tasks=new LinkedList<Task>();
	//����ȫ�ֵĵ���
	public static MainService mainService=null;
	//�̴߳���ı�־��Ϣ
	public static boolean isRun=true;
	//��ǰ������
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
		//�������״̬�仯�Ĺ㲥������
		this.registerReceiver(netReceiver, 
				new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
		
	}
	
	//����ˢ��UI���棬��������id��
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

	
	//�����̴߳�������,��������id��
	@Override
	public void run() {
		Log.i(TAG,"thread is running");
		while(isRun){
			//���ڲ�ѯ������е���������������񣬾ʹ��������
			//Log.i(TAG,"now, amount of activites is "+activities.size());
			if(tasks.size()!=0){
				Thread t=new DoTaskThread(tasks.removeFirst());
				t.start();
			}else{
				//����ǰû���������߳���Ϣһ��ʱ����ִ��
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
				//���������
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
	 //��ʾ�Ƿ��˳�Ӧ�ó���
    public static void promptExitApp(final Activity context)
    {
       //�����Ի���
    	AlertDialog.Builder ab=new AlertDialog.Builder(context);
    	
    	ab.setMessage("�Ƿ��˳�?");
    	ab.setPositiveButton("�˳�", 
    		new OnClickListener()
    	     {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss();
					quit(context);//�˳����Ӧ��
				}
    	     }
    	    );
    	ab.setNegativeButton("���˳�", null);
    	ab.show();
    }
    
	//��ʾ�û������쳣
	public static void alertNetError(final Activity context)
	{
	   	AlertDialog.Builder ab=new AlertDialog.Builder(context);
	   	//�趨����
	   	ab.setTitle("�����쳣");
	  //�趨����
	   	ab.setMessage("��������");
	   	//�趨�˳���ť
	   	ab.setNegativeButton("�˳�����",
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
		//�������ð�ť
	   	ab.setPositiveButton("��������",
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
