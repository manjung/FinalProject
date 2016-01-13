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
	
	private int ScreenWeight;  				//�ù��e��
	private int ScreenHeight;  				//�ù�����
	private float startWeight; 		 		//�ѽLx�b�}�l�y��
	private float starHeight;   			//�ѽLy�b�}�l�y��
	private int rowNum = 2;     			//���V���
	private int colNum = 2;     			//��V���
	private int boundary ;      			//���
	private int lattice ;       			//�C�j�e��
	private boolean contorlBoundary ;
	private Bitmap backGroupBitmap;
	private int textSize;                   //�r���j�p
	private PlayerRecord playerRecord;
	
	private Player	playerRed;
	private Player	playerBlack;
	
	private Paint paint_Text;                //�e��

	
	public PlayerView(Context context,PlayerRecord playerRe)
	{
		super(context);
		this.playerRecord = playerRe;
		
		
	}
	
	public void set(int width,int height)   //�]�w�ѽL�j�p
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
	
	public void setBoundary()   //�]�w���
	{
		int t = this.ScreenWeight/(colNum+4);
		
			boundary = t;
	}
	
	public void setLattice()     //�]�w�C��e��
	{
		lattice = boundary;
	}
	
	public void setTextSize()      //�]�w�r���j�p
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
	
	public void setChessBoardCoordinate() // �]�w�ѽL���_�I�B���I
	{
		
		if(contorlBoundary)
		{
			startWeight = boundary;
			starHeight = boundary; 
			
		}else
		{
			startWeight = boundary;
			starHeight = boundary;  //�i�b�վ����᪺�ѽL��m
			
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
	
	protected void onDraw(Canvas canvas)    //�}�l�G��
	{
		Paint paint = new Paint();
		canvas.drawColor(getResources().getColor(R.color.white));
		canvas.drawBitmap(backGroupBitmap,0,0,paint);
		drawPlayer(canvas);
		super.onDraw(canvas);
	}
	
	public void drawPlayer(Canvas canvas)
	{
		canvas.drawText("����G"+playerRed.getName(), startWeight, starHeight, paint_Text);
		canvas.drawText("�¤�G"+playerBlack.getName(), startWeight, starHeight+boundary, paint_Text);
	}
	
	
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)    //���qview���j�p
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
