package com.example.gameproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;


public class PlayerView extends View
{
	private final int MIN_SIZE = 600;
	
	private int ScreenWeight;  				//螢幕寬度
	private int ScreenHeight;  				//螢幕高度
	private float startWeight; 		 		//棋盤x軸開始座標
	private float starHeight;   			//棋盤y軸開始座標
	private int rowNum = 2;     			//直向格數
	private int colNum = 2;     			//橫向格數
	private int boundary ;      			//邊界
	private int lattice ;       			//每隔寬度
	private boolean contorlBoundary ;
	private Bitmap backGroupBitmap;
	private int textSize;                   //字的大小
	private PlayerRecord playerRecord;
	
	private Player	playerRed;
	private Player	playerBlack;
	
	private Paint paint_Text;                //畫筆

	
	public PlayerView(Context context,PlayerRecord playerRe)
	{
		super(context);
		this.playerRecord = playerRe;
		
		
	}
	
	public void set(int width,int height)   //設定棋盤大小
	{
		contorlBoundary = true;
			
		this.ScreenWeight = width;
		this.ScreenHeight = height;
		
		initial();
	}
	
	public void initial()
	{
		setPlayer();
		setTableColor();
		setBoundary() ;
		setLattice();
		setChessBoardCoordinate();
		setTextSize();
		setPaint();
	}
	
	public void setPlayer()
	{
		this.playerRed = playerRecord.getPlayerRed();
		this.playerBlack = playerRecord.getPlayerBlack();
		
	}
	
	
	public void setTableColor()
	{
		backGroupBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
		
	}
	
	public void setBoundary()   //設定邊界
	{
		int t = this.ScreenWeight/(colNum+4);
		
			boundary = t;
	}
	
	public void setLattice()     //設定每格寬度
	{
		lattice = boundary;
	}
	
	public void setTextSize()      //設定字型大小
	{
		textSize = lattice/2;
	}
	
	public boolean checkOK(int width,int height)
	{
		if(width < height)
			return true;
		else
			return false;
	}
	
	public void setChessBoardCoordinate() // 設定棋盤的起點、終點
	{
		
		if(contorlBoundary)
		{
			startWeight = boundary;
			starHeight = boundary; 
			
		}else
		{
			startWeight = boundary;
			starHeight = boundary;  //可在調整反轉後的棋盤位置
			
		}
		
	}
	
	public void setPaint() 
	{
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		paint_Text = new Paint();
		paint_Text.setColor(getResources().getColor(R.color.black));
		paint_Text.setTextSize(textSize);
		paint_Text.setTypeface( font );
		
	}
	
	public void setPaintColor()
	{
		paint_Text.setColor(getResources().getColor(R.color.red));
		
	}
	
	protected void onDraw(Canvas canvas)    //開始佈局
	{
		Paint paint = new Paint();
		canvas.drawColor(getResources().getColor(R.color.white));
		canvas.drawBitmap(backGroupBitmap,0,0,paint);
		drawPlayer(canvas);
		super.onDraw(canvas);
	}
	
	public void drawPlayer(Canvas canvas)
	{
		canvas.drawText("紅方："+playerRed.getName(), startWeight, starHeight, paint_Text);
		canvas.drawText("黑方："+playerBlack.getName(), startWeight, starHeight+boundary, paint_Text);
	}
	
	
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)    //測量view的大小
	{
        this.setMeasuredDimension(getMeasuredSize(widthMeasureSpec),
        						  getMeasuredSize(heightMeasureSpec));
        
        set(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getMeasuredSize(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) 
        {
        	return MIN_SIZE;
        } 
        else 
        {
            // mode == MeasureSpec.EXACTLY
            int size = MeasureSpec.getSize(measureSpec);
            if (size >= MIN_SIZE) 
            	return size;
             else 
            	return MIN_SIZE;
            
        }
    }
}
