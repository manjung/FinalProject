package com.example.gameproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LongChessView extends Activity {
	
	
	private static int BLACK = 1;
	private static int RED = 0;
	//0：出棋，1：等待，2：贏，-1：輸
	private static int ON = 0;
	private static int WAIT = 1;
	private static int WIN = 2;
	private static int LOSE = -1;
	
	private boolean LIVE = true;
	private boolean DEAD = false;
	
	Chess[] 	       		  RedChess   		  ;
	Chess[] 	              BlackChess          ;
	Player					  playerRed           ;
	Player					  playerBlack         ;
	
	LinearLayout              layout              ;
	LinearLayout              layout2             ;
	LongChessViewGroup        viewGroup           ;
	
	LongChessRecord           LCR                 ;
	LongChessBoardView        CV 	              ;
	LongChessControler        LCC                 ;
	PlayerView                PV                  ;
	PlayerRecord              PR                  ;
	PlayerControl			  PC                  ;
	LongChessBridge           LB                  ;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);   //全螢幕設定，不設定Y軸座標會偏移
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 DisplayMetrics metrics = new DisplayMetrics();  
		 getWindowManager().getDefaultDisplay().getMetrics(metrics);
		 
		 Bundle bundle = getIntent().getExtras();  
		 String player1 = bundle.getString("player1");
		 String player2 = bundle.getString("player2");
		 
		 creatPlayer(player1,player2);
		 creatChess();
		 
		 LB = new LongChessBridge();
		 
		 LCR= new LongChessRecord(BlackChess,RedChess,9,10);                   //Chess Model
		 PR = new PlayerRecord(playerRed,playerBlack);                         //Player Model
		 
		 CV = new LongChessBoardView(LongChessView.this,LCR);                  //Chess View
		 PV  = new PlayerView(LongChessView.this,PR);                          //Player View
		 
		 LCC = new LongChessControler(LCR,CV);                                 //Chess Controler
		 PC = new PlayerControl(PR,PV);                                        //Player Controler
		 
		 LB.setLongChessControl(LCC);
		 LB.setPlayerControl(PC);
		 LCC.setBridge(LB);
		 
		 layout = new LinearLayout(this);
		 layout2 = new LinearLayout(this);
		 viewGroup = new LongChessViewGroup(this);
		 
		 layout.setOrientation(LinearLayout.VERTICAL);
		 layout.setBackgroundColor(Color.BLACK);
		 layout.addView(CV);
		 
		 layout2.setOrientation(LinearLayout.VERTICAL);
		 layout2.setBackgroundColor(Color.WHITE);
		 layout2.addView(PV);
		 
		 viewGroup.addView(layout, new LinearLayout.LayoutParams(metrics.widthPixels, 
				 ((metrics.heightPixels)/3)*2));
		 viewGroup.addView(layout2, new LinearLayout.LayoutParams(metrics.widthPixels, 
				 metrics.heightPixels));
		 
		 setContentView(viewGroup);
		 
	}
	
	
	public void creatChess()
	{
		BlackChess = new Chess[]{
				new Chess(BLACK,"車",2,0,0,LIVE),
				new Chess(BLACK,"馬",3,1,0,LIVE),
				new Chess(BLACK,"象",4,2,0,LIVE),
				new Chess(BLACK,"士",5,3,0,LIVE),
				new Chess(BLACK,"將",6,4,0,LIVE),
				new Chess(BLACK,"士",5,5,0,LIVE),
				new Chess(BLACK,"象",4,6,0,LIVE),
				new Chess(BLACK,"馬",3,7,0,LIVE),
				new Chess(BLACK,"車",2,8,0,LIVE),
				new Chess(BLACK,"包",1,1,2,LIVE),
				new Chess(BLACK,"包",1,7,2,LIVE),
				new Chess(BLACK,"卒",7,0,3,LIVE),
				new Chess(BLACK,"卒",7,2,3,LIVE),
				new Chess(BLACK,"卒",7,4,3,LIVE),
				new Chess(BLACK,"卒",7,6,3,LIVE),
				new Chess(BLACK,"卒",7,8,3,LIVE)};
		
		RedChess 	= new Chess[]{
				new Chess(RED,"車",2,0,9,LIVE),
				new Chess(RED,"馬",3,1,9,LIVE),
				new Chess(RED,"相",4,2,9,LIVE),
				new Chess(RED,"士",5,3,9,LIVE),
				new Chess(RED,"帥",6,4,9,LIVE),
				new Chess(RED,"士",5,5,9,LIVE),
				new Chess(RED,"相",4,6,9,LIVE),
				new Chess(RED,"馬",3,7,9,LIVE),
				new Chess(RED,"車",2,8,9,LIVE),
				new Chess(RED,"炮",1,1,7,LIVE),
				new Chess(RED,"炮",1,7,7,LIVE),
				new Chess(RED,"兵",7,0,6,LIVE),
				new Chess(RED,"兵",7,2,6,LIVE),
				new Chess(RED,"兵",7,4,6,LIVE),
				new Chess(RED,"兵",7,6,6,LIVE),
				new Chess(RED,"兵",7,8,6,LIVE)};
		
	}
	
	
	public void creatPlayer(String p1,String p2)
	{
		playerRed   = new Player(p1,RED,ON);
		playerBlack = new Player(p2,BLACK,WAIT);
		
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		float touchPointX =  event.getX();
		float touchPointY =  event.getY();
		
		LCC.set();
		
		switch(event.getAction())
		{
		 case MotionEvent.ACTION_DOWN:
			  LCC.c_TouchDown(touchPointX,touchPointY);
			  break;
		 case MotionEvent.ACTION_UP:
			  LCC.c_TouchUp(touchPointX,touchPointY);
			  CV.postInvalidate();
		 	  break;
		 case MotionEvent.ACTION_MOVE:
			  LCC.c_TouchMove(touchPointX,touchPointY);
			  
			  break;
		 default:
			  break;
		}
		
		return super.onTouchEvent(event);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.long_chess_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
