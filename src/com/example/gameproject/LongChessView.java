package com.example.gameproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class LongChessView extends Activity {
	
	private static int BLACK = 1;
	private static int RED = 0;
	
	ChessBoard1        chessBoard1 	      ;
	Chess[] 	       RedChess   		  ;
	Chess[] 	       BlackChess         ;
	LinearLayout       layout             ;
	LongChessViewGroup viewGroup          ;
	

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_long_chess_view);
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);   //全螢幕設定
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		 DisplayMetrics metrics = new DisplayMetrics();  
		 getWindowManager().getDefaultDisplay().getMetrics(metrics);
		 creatChess();
		 
		 chessBoard1 = new ChessBoard1(LongChessView.this,BlackChess,RedChess);
		 /*layout = new LinearLayout(this);
		 viewGroup = new LongChessViewGroup(this);
		
		 
		 layout.setOrientation(LinearLayout.VERTICAL);
		 layout.setBackgroundColor(Color.WHITE);
		 layout.addView(chessBoard1);
		 viewGroup.addView(layout, new LinearLayout.LayoutParams(100, 100));
		 setContentView(chessBoard1);
	}*/
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_long_chess_view);
		
		 creatChess();
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);   //全螢幕設定，不設定Y軸座標會偏移
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
		 chessBoard1 = new ChessBoard1(LongChessView.this,BlackChess,RedChess);
		 layout = new LinearLayout(this);
		 viewGroup = new LongChessViewGroup(this);
		 
		 DisplayMetrics metrics = new DisplayMetrics();  
		 getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		 //layout.setBackgroundResource(R.drawable.roundbox);
		 layout.setOrientation(LinearLayout.VERTICAL);
		 layout.setBackgroundColor(Color.BLACK);
		 layout.addView(chessBoard1);
		 viewGroup.addView(layout, new LinearLayout.LayoutParams(metrics.widthPixels, 
				 ((metrics.heightPixels)/3)*2));
		 Log.v("metrics.heightPixels", Integer.toString(metrics.heightPixels/3));
		 setContentView(viewGroup);
	}
	
	
	public void creatChess()
	{
		BlackChess = new Chess[]{
				new Chess(BLACK,"車",2,0,0),
				new Chess(BLACK,"馬",3,1,0),
				new Chess(BLACK,"象",4,2,0),
				new Chess(BLACK,"士",5,3,0),
				new Chess(BLACK,"將",6,4,0),
				new Chess(BLACK,"士",5,5,0),
				new Chess(BLACK,"象",4,6,0),
				new Chess(BLACK,"馬",3,7,0),
				new Chess(BLACK,"車",2,8,0),
				new Chess(BLACK,"包",1,1,2),
				new Chess(BLACK,"包",1,7,2),
				new Chess(BLACK,"卒",0,0,3),
				new Chess(BLACK,"卒",0,2,3),
				new Chess(BLACK,"卒",0,4,3),
				new Chess(BLACK,"卒",0,6,3),
				new Chess(BLACK,"卒",0,8,3)};
		
		RedChess 	= new Chess[]{
				new Chess(RED,"車",2,0,9),
				new Chess(RED,"馬",3,1,9),
				new Chess(RED,"相",4,2,9),
				new Chess(RED,"士",5,3,9),
				new Chess(RED,"帥",6,4,9),
				new Chess(RED,"士",5,5,9),
				new Chess(RED,"相",4,6,9),
				new Chess(RED,"馬",3,7,9),
				new Chess(RED,"車",2,8,9),
				new Chess(RED,"炮",1,1,7),
				new Chess(RED,"炮",1,7,7),
				new Chess(RED,"兵",0,0,6),
				new Chess(RED,"兵",0,2,6),
				new Chess(RED,"兵",0,4,6),
				new Chess(RED,"兵",0,6,6),
				new Chess(RED,"兵",0,8,6)};
		
	}

	
	public boolean onTouchEvent(MotionEvent event)
	{
		float touchPointX =  event.getX();
		float touchPointY =  event.getY();
		
		switch(event.getAction())
		{
		 case MotionEvent.ACTION_DOWN:
			  chessBoard1.c_TouchDown(touchPointX,touchPointY);
			  break;
		 case MotionEvent.ACTION_UP:
			  chessBoard1.c_TouchUp(touchPointX,touchPointY);
		 	  break;
		 case MotionEvent.ACTION_MOVE:
			  chessBoard1.c_TouchMove(touchPointX,touchPointY);
		 	  break;
		 default:
			  break;
		}
		
		return super.onTouchEvent(event);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.long_chess_view, menu);
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
