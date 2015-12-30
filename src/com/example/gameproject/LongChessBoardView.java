package com.example.gameproject;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;



public class LongChessBoardView extends View{
	
	private final int MIN_SIZE = 600;
	
	private int ScreenWeight;  				//螢幕寬度
	private int ScreenHeight;  				//螢幕高度
	private float startWeight; 		 		//棋盤x軸開始座標
	private float starHeight;   			//棋盤y軸開始座標
	private float endtWeight;   			//棋盤x軸結束座標
	private float endHeight;    			//棋盤y軸結束座標
	private int boundary ;      			//邊界
	private int lattice ;       			//每隔寬度
	private int rowNum = 9;     			//直向格數
	private int colNum = 8;     			//橫向格數
	private int tableColor ;    			//背景色
	private float circleRadiu ;  			//棋子半徑大小
	private Paint paint_Chess ;             //棋子畫筆
	private Paint paint_BLACKText;          //黑方畫筆
	private Paint paint_REDText;            //紅方畫筆
	private Paint paint_Line;				//棋盤畫筆
	private Paint paint_Back ;              //背景畫筆
	private Paint Paint_ears;               //擦子
    private int textSize;                   //棋中字的大小
	private boolean contorlBoundary ;
	private Bitmap backGroupBitmap;
	private LongChessRecord longCR;         //棋盤紀錄
	private Chess[] BlackChess;
	private Chess[] RedChess;

	public LongChessBoardView(Context context,LongChessRecord longCR)
	{
		super(context);
		this.longCR = longCR;
		backGroupBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
	}
	
	public void setChess()
	{
		this.BlackChess = longCR.getBlackChess();
		this.RedChess = longCR.getRedChess();
	}
	
	public boolean checkOK(int width,int height)
	{
		if(width < height)
			return true;
		else
			return false;
	}
	
	public void set(int width,int height)   //設定棋盤大小
	{
		contorlBoundary = true;
		
		if(checkOK(width,height))
		{
			this.ScreenWeight = width;
			this.ScreenHeight = height;
			
		}else
		{
			this.ScreenWeight = height;
			this.ScreenHeight = width;
			contorlBoundary = false;
		}	
		
		initial();
		
		
	}
	
	public void initial()   //初始化各個物件
	{
		setChess();
		setBoundary();
		setLattice();
		setChessBoardCoordinate();
		setChessRadiu();
		setTableColor();
		setTextSize();
		setPaint();
		
	}
	
	public void setBoundary()   //設定邊界
	{
		int t = this.ScreenWeight/(colNum+2);
		if( contorlBoundary )
		{
			boundary = t;
		}else
		{
			if( t*(rowNum+2) < this.ScreenHeight )
			{
				boundary = this.ScreenWeight/(rowNum+2);
			}else
				boundary = t;
		}
		
	}
	
	
	public void setLattice()     //設定每格寬度
	{
		lattice = boundary;
	}
	
	public void setChessBoardCoordinate() // 設定棋盤的起點、終點
	{
		
		if(contorlBoundary)
		{
			startWeight = boundary;
			starHeight = boundary; 
			//starHeight = (this.ScreenHeight-boundary*rowNum)/2; //棋盤位置置中
			endtWeight = startWeight + colNum*lattice;
			endHeight = starHeight + rowNum*lattice;
		}else
		{
			startWeight = boundary;
			starHeight = boundary;  //可在調整反轉後的棋盤位置
			endtWeight = startWeight + colNum*lattice;
			endHeight = starHeight + rowNum*lattice;
			
		}
		
	}
	
	public void setTableColor()   //設定底色
	{
		tableColor = getResources().getColor(R.color.black);
	}
	
	public void setChessRadiu()   //設定棋子半徑
	{
		circleRadiu = lattice/2;
	}
	
	public void setTextSize()      //設定字型大小
	{
		textSize = (int)circleRadiu;
	}
	
	public void setPaint()        //設定畫筆
	{
		Shader mShader = new LinearGradient(0, 0, 80, 80, new int[] {  
				Color.WHITE, Color.YELLOW }, null,  
                Shader.TileMode.REPEAT);  
        
		paint_Chess = new Paint();
		paint_Chess.setStrokeCap(Paint.Cap.ROUND);
		paint_Chess.setShader(mShader);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		
		paint_BLACKText = new Paint();
		paint_BLACKText.setColor(getResources().getColor(R.color.black));
		paint_BLACKText.setTextSize(textSize);
		paint_BLACKText.setTypeface( font );
		paint_REDText = new Paint();
		paint_REDText.setColor(getResources().getColor(R.color.red));
		paint_REDText.setTextSize(textSize);
		paint_REDText.setTypeface( font );
		paint_Line = new Paint();
		paint_Line.setColor(getResources().getColor(R.color.white));
		paint_Line.setStrokeWidth(5);
		paint_Line.setStrokeCap(Paint.Cap.ROUND);
		paint_Line.setStrokeWidth(7);
		
		Paint_ears = new Paint();
		Paint_ears.setColor(tableColor);
		Paint_ears.setStrokeWidth(5);
		Paint_ears.setStrokeWidth(7);
		
	}

	public void drawChessBoard(Canvas canvas)    //畫棋盤
	{
		
		// 畫橫線
		for(int i=0 ;  i<=rowNum; i++ )
			canvas.drawLine(startWeight,starHeight+i*lattice,endtWeight,starHeight+i*lattice,paint_Line);
		
		// 畫直線
		for(int i=0 ;  i<=colNum; i++ )
			canvas.drawLine(startWeight+i*lattice,starHeight,startWeight+i*lattice,endHeight,paint_Line);
		
		// 畫四條斜線
		canvas.drawLine(startWeight+3*lattice,starHeight,startWeight+5*lattice,starHeight+2*lattice,paint_Line);
		canvas.drawLine(startWeight+5*lattice,starHeight,startWeight+3*lattice,starHeight+2*lattice,paint_Line);
		canvas.drawLine(startWeight+3*lattice,starHeight+7*lattice,startWeight+5*lattice,starHeight+9*lattice,paint_Line);
		canvas.drawLine(startWeight+5*lattice,starHeight+7*lattice,startWeight+3*lattice,starHeight+9*lattice,paint_Line);
		
		// 設定畫筆效果變成擦子,i=1 && i<= colNum-1(除了第一條和最後一條，中間都要擦掉)
		for(int i=1 ; i<= colNum-1 ; i++ )
			canvas.drawLine(startWeight+i*lattice,starHeight+lattice*4,startWeight+i*lattice,starHeight+lattice*5,Paint_ears);
		
	}
	
	public void drawChess(Canvas canvas)     //畫棋子
	{
		
		for(int i=0 ; i < RedChess.length ; i++)
		{
			if(RedChess[i].getStatus())
			{
				canvas.drawCircle(startWeight+RedChess[i].getXLoc()*lattice,
					starHeight+RedChess[i].getYLoc()*lattice,circleRadiu,paint_Chess);
				canvas.drawText(RedChess[i].getName(), startWeight+RedChess[i].getXLoc()*lattice-circleRadiu/2, 
					starHeight+RedChess[i].getYLoc()*lattice+circleRadiu/2, paint_REDText);
			}
			
			
		}
		
		for(int i=0 ; i < BlackChess.length ; i++)
		{
			if(BlackChess[i].getStatus())
			{
			   canvas.drawCircle(startWeight+BlackChess[i].getXLoc()*lattice,
					starHeight+BlackChess[i].getYLoc()*lattice,circleRadiu,paint_Chess);
			   canvas.drawText(BlackChess[i].getName(), startWeight+BlackChess[i].getXLoc()*lattice-circleRadiu/2, 
					starHeight+BlackChess[i].getYLoc()*lattice+circleRadiu/2, paint_BLACKText);
				
			}
			
			
		}
		
	}
	
	protected void onDraw(Canvas canvas)    //開始佈局
	{
		setChess();
		canvas.drawColor(tableColor);
		//canvas.drawBitmap(backGroupBitmap,0,0,paint_Back);
		drawChessBoard(canvas);
		drawChess(canvas);
		
	}
	
	
	
	public float getStartWeight()
	{
		
		return startWeight;
	}
	
	public float getStarHeight()
	{
		
		return starHeight;
	}
	
	public int getLattice()
	{
		return lattice;
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
