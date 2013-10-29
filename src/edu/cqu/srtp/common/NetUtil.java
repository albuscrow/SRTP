package edu.cqu.srtp.common;

import java.net.HttpURLConnection;
import java.net.URL;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil extends BroadcastReceiver{
	//��������״̬�ı仯
	  public void onReceive( Context context, Intent intent ) 
	  { 
	    ConnectivityManager connectivityManager = (ConnectivityManager)
	        context.getSystemService( Context.CONNECTIVITY_SERVICE ); 
	              NetworkInfo info = connectivityManager.getActiveNetworkInfo(); 
	            if (info != null&& info.isConnected()) { 
	                // �жϵ�ǰ�����Ƿ��Ѿ����� 
	                if (info.getState() == NetworkInfo.State.CONNECTED) { 
	                 // Toast.makeText( context, "ok", Toast.LENGTH_SHORT ).show(); 
	                }else
	                {
	                 //Toast.makeText( context, "err", Toast.LENGTH_SHORT ).show(); 
	                }
	            }else
	                {
	                  //Toast.makeText( context, "err", Toast.LENGTH_SHORT ).show(); 
	                }        
	   } 

	public static boolean checkNet(Context context)
	{// ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ��? 
	    try { 
	        ConnectivityManager connectivity = (ConnectivityManager) context 
	                .getSystemService(Context.CONNECTIVITY_SERVICE); 
	        if (connectivity != null) { 
	            // ��ȡ�������ӹ���Ķ��� 
	            NetworkInfo info = connectivity.getActiveNetworkInfo(); 
	            if (info != null&& info.isConnected()) { 
	                // �жϵ�ǰ�����Ƿ��Ѿ����� 
	                if (info.getState() == NetworkInfo.State.CONNECTED) { 
	                    return true; 
	                }        }        } 
	    } catch (Exception e) { 
	} 
	      return false; 
	}
	//��һ��URL��ȡͼƬ
	@SuppressWarnings("deprecation")
	public static BitmapDrawable getImageFromURL(URL url)
	{BitmapDrawable bd=null;
	 try{
	    //��������
		 HttpURLConnection hc=(HttpURLConnection)url.openConnection();
		//��ȡ��� 
		bd=new BitmapDrawable(hc.getInputStream()); 
        //�ر�����	
		 hc.disconnect();
		}catch(Exception e){}
	 return bd;
	}

}
