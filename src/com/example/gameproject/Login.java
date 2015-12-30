package com.example.gameproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Login extends Activity {

	private  String player1;
	private  String player2;
	private  EditText inputName;
	private  EditText inputName2;
	private  Builder dialog;
	//private  Builder dialog2;
	private  LayoutInflater inflater;
	private  LinearLayout layout;
	//private  LinearLayout layout2;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout)inflater.inflate(R.layout.dialogview, null);
		//layout2 = (LinearLayout)inflater.inflate(R.layout.dialogview, null);
		inputName = (EditText)layout.findViewById(R.id.editText1);
		inputName2 = (EditText)layout.findViewById(R.id.editText2);
		
		setPlayer();
	}
	
	private void setPlayer() {
		
		dialog = new AlertDialog.Builder(this);
		dialog.setView(layout);
		dialog.setTitle("請輸入玩家姓名：") ;
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() 
		{
		
			public void onClick(DialogInterface dialog, int which) 
			{
		
				player1 = inputName.getText().toString();
				player2 = inputName2.getText().toString();
				Intent intent = new Intent();
			    intent.setClass(Login.this,LongChessView.class);
			    Bundle bundle = new Bundle();
			    bundle.putString("player1", player1);
			    bundle.putString("player2", player2);
		        intent.putExtras(bundle);
			    startActivity(intent);
				
				
			}
		});
		 
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) 
		{
		   //設定取消後的動作：回上一頁。
		
		}
		});
		
		dialog.show();
		
	}   
		
	/*private void setPlayer2() {
		
		
		dialog2 = new AlertDialog.Builder(this);
		dialog2.setView(layout);
		dialog2.setTitle("請輸入第二位玩家姓名：") ;
	    dialog2.setPositiveButton("确定", new DialogInterface.OnClickListener() 
		{
			 public void onClick(DialogInterface dialog, int which) 
			 {
				 
				    player2= inputName2.getText().toString();
				  
				    
			   }
		   }).setNegativeButton("取消", null).show();
		
		dialog2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) 
			{
			   //設定取消後的動作：回上一頁。
			
			}
			});
		
		dialog2.show();
		    
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
