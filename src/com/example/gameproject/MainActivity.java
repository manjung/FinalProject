package com.example.gameproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	
	private						 Button							button1				   ;
	private						 Button							button2				   ;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		
		button1.setOnClickListener(new Button.OnClickListener()
		{
			@Override
		   public void onClick(View v) 
		   {	 
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,LongChessView.class);
				startActivity(intent);
           }         

       });
		
		button2.setOnClickListener(new Button.OnClickListener()
		{
			@Override
		   public void onClick(View v) 
		   {	
				
           }         

       });	
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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