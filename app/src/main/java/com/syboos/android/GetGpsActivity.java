package com.syboos.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.*;
import java.net.*;
import java.util.List;

import com.loopj.android.http.AsyncHttpClient;

import android.text.format.Time;
import android.util.Log;
//import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



public class GetGpsActivity extends Activity implements OnClickListener{
    private double iIdo = 0;
	private double iKeido = 0;
	public EditText abc;
	public String light_value = "";
	private SensorManager mSensorManager;
	 private TextView tv_search_result;
	 private WifiManager wifi_mng;
	 //private WifiReceiver wifi_rec;
	 private String abc1;
	 private int num = 0;
	private List<ScanResult> result_list;
	 int o=0;


    @Override  
    public void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.main);

        
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(botton1_listener);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(botton2_listener);
        
        	
    }
    
    @Override
    public void onResume(){
    	super.onResume();

    }
    

Button.OnClickListener botton1_listener = new Button.OnClickListener(){
		public void onClick(View arg0) {
			
			tv_search_result = (TextView) findViewById(R.id.textView2);
			wifi_mng = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			//wifi_rec = new WifiReceiver();
			//registerReceiver(wifi_rec, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
			wifi_mng.startScan();
			tv_search_result.setText("�X�L�����J�n\n");
			result_list = wifi_mng.getScanResults();
			
			abc = (EditText) findViewById(R.id.editText1);
			abc1 = abc.getText().toString();
			String msgbuf = "";
			for (int i = 0; i < result_list.size(); i++) {
				BufferedReader inmsg = null;{
					try
					{
						URL TestURL = new URL("http://bbr-1352de4.iobb.net/write.php");
	      	                                           
						URLConnection con = TestURL.openConnection();
	      	                                         
		      	     con.setDoOutput(true);
		      	                                        
		      	     PrintStream out = new PrintStream(con.getOutputStream(),true, "UTF-8");
		      	     
		      	     msgbuf = "SSID="+result_list.get(i).SSID+"&BSSID="+result_list.get(i).BSSID+"&LEVEL="+result_list.get(i).level+"&No="+abc1;
		      	     out.print(msgbuf);
		      	     out.close();
		      	     //System.out.println(abc1);
		      	     
		      	      inmsg = new BufferedReader(new InputStreamReader(con.getInputStream()));
	
		      	      String line;
		      	      while ((line = inmsg.readLine()) != null)
		      	      {
		      	                                       
		      	                  System.out.println(line);
		      	        }	                                         
		      	         inmsg.close();
		      	    }
		      	    catch (Exception e){}
		      	}
			   }
			}
		};
    
Button.OnClickListener botton2_listener = new Button.OnClickListener(){
public void onClick(View arg0) {
	
//			abc = (EditText) findViewById(R.id.editText1);
//			abc1 = abc.getText().toString();
//			String msgbuf = "";
//			for (int i = 0; i < result_list.size(); i++) {
//				BufferedReader inmsg = null;{
//					try
//					{
//						URL TestURL = new URL("http://133.15.166.203/write.php");
//	      	                                           
//						URLConnection con = TestURL.openConnection();
//	      	                                         
//		      	     con.setDoOutput(true);
//		      	                                        
//		      	     PrintStream out = new PrintStream(con.getOutputStream(),true, "UTF-8");
//		      	     num = 1;
//		      	     msgbuf = "SSID="+result_list.get(i).SSID+"&BSSID="+result_list.get(i).BSSID+"&LEVEL="+result_list.get(i).level+"&No="+abc1+num;
//		      	     out.print(msgbuf);
//		      	     out.close();
//		      	     //System.out.println(abc1);
//		      	     
//		      	      inmsg = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	
//		      	      String line;
//		      	      while ((line = inmsg.readLine()) != null)
//		      	      {
//		      	                                       
//		      	                  System.out.println(line);
//		      	        }	                                         
//		      	         inmsg.close();
//		      	    }
//		      	    catch (Exception e){}
//		      	}
//			   }
//	
//				
		}
	};
    
    
		//class WifiReceiver extends BroadcastReceiver {

//			  @Override
//			  public void onReceive(Context context, Intent intent) {
//			   // TODO �����������ꂽ���\�b�h�E�X�^�u
//				  System.out.println("test");
//			   StringBuffer sb = new StringBuffer();
//			   result_list = wifi_mng.getScanResults();
//			   for (int i = 0; i < result_list.size(); i++) {
//			    //sb.append("BSSID:"+ result_list.get(i).BSSID + " SSID:" + result_list.get(i).SSID + " CAP:" + result_list.get(i).capabilities + " FRE:" + result_list.get(i).frequency + " LEVEL:" + result_list.get(i).level);
//				sb.append("SSID:" + result_list.get(i).SSID + " BSSID:" + result_list.get(i).BSSID + " LEVEL:" + result_list.get(i).level);
//			    sb.append("\n");
//			    //System.out.println(result_list.get(1).SSID);
//			   }
//			   tv_search_result.setText(sb);
//			   
//			   
//			  }
			  
			 
		
		//}


		
		public void sendmethod() {
			abc = (EditText) findViewById(R.id.editText1);
			abc1 = abc.getText().toString();
			String msgbuf = "";
			for (int i = 0; i < result_list.size(); i++) {
				BufferedReader inmsg = null;{
					try
					{
						//URL TestURL = new URL("http://133.15.166.203/write.php");
						URL TestURL = new URL("http://bbr-1352de4.iobb.net/write.php");
	      	                                           
						URLConnection con = TestURL.openConnection();
	      	                                         
		      	     con.setDoOutput(true);
		      	                                        
		      	     PrintStream out = new PrintStream(con.getOutputStream(),true, "UTF-8");
		      	     num = 1;
		      	     msgbuf = "SSID="+result_list.get(i).SSID+"&BSSID="+result_list.get(i).BSSID+"&LEVEL="+result_list.get(i).level+"&No="+abc1+num;
		      	     out.print(msgbuf);
		      	     out.close();
		      	     //System.out.println(abc1);
		      	     
		      	      inmsg = new BufferedReader(new InputStreamReader(con.getInputStream()));
	
		      	      String line;
		      	      while ((line = inmsg.readLine()) != null)
		      	      {
		      	                                       
		      	                  System.out.println(line);
		      	        }	                                         
		      	         inmsg.close();
		      	    }
		      	    catch (Exception e){}
		      	}
			   }
	
		
		}

		@Override
		public void onClick(View v) {
			// TODO �����������ꂽ���\�b�h�E�X�^�u
			
		}
		
}

