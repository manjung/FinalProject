package com.example.gameproject;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;


public class ChessBoard1 extends View{
	
	private final int MIN_SIZE = 600;
	
	private int ScreenWeight;  				//�ù��e��
	private int ScreenHeight;  				//�ù�����
	private float startWeight; 		 		//�ѽLx�b�}�l�y��
	private float starHeight;   			//�ѽLy�b�}�l�y��
	private float endtWeight;   			//�ѽLx�b�����y��
	private float endHeight;    			//�ѽLy�b�����y��
	private int boundary ;      			//���
	private int lattice ;       			//�C�j�e��
	private int rowNum = 9;     			//���V���
	private int colNum = 8;     			//��V���
	private int tableColor ;    			//�I����
	private float circleRadiu ;  			//�Ѥl�b�|�j�p
	private Paint paint_Chess ;             //�Ѥl�e��
	private Paint paint_BLACKText;          //�¤�e��
	private Paint paint_REDText;            //����e��
	private Paint paint_Line;				//�ѽL�e��
	private Paint Paint_ears;               //���l
    private int textSize;                   //�Ѥ��r���j�p
	private float[][] circleLocX  ;
	private float[][] circleLocY ;
	private float touchPointX = 0;
	private float touchPointY = 0;
	private boolean IstouchDownCircle;  //�w���U�� 
	private int Num;                    //�������U�����Ѥl
	private int colorside;              //�����{�b�O���@��U��
	private int lastColorside;          //�����W�@���O���@��U��
	
	private boolean contorlBoundary ;
	
	private Chess[] BlackChess;
	private Chess[] RedChess;

	public ChessBoard1(Context context,Chess[] BChess,Chess[] RChess)
	{
		super(context);
		this.BlackChess = BChess;
		this.RedChess = RChess;
	}
	
	public boolean checkOK(int width,int height)
	{
		if(width < height)
			return true;
		else
			return false;
	}
	
	public void set(int width,int height)   //�]�w�ѽL�j�p
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
	
	public void initial()   //��l�ƦU�Ӫ���
	{
		setBoundary();
		setLattice();
		setChessBoardCoordinate();
		setChessRadiu();
		setTableColor();
		setChess();
		setTextSize();
		setPaint();
		
		
	}
	
	public void setBoundary()   //�]�w���
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
	
	
	public void setLattice()     //�]�w�C��e��
	{
		lattice = boundary;
	}
	
	public void setChessBoardCoordinate() // �]�w�ѽL���_�I�B���I
	{
		
		if(contorlBoundary)
		{
			startWeight = boundary;
			starHeight = boundary; 
			//starHeight = (this.ScreenHeight-boundary*rowNum)/2; //�ѽL��m�m��
			endtWeight = startWeight + colNum*lattice;
			endHeight = starHeight + rowNum*lattice;
		}else
		{
			startWeight = boundary;
			starHeight = boundary;  //�i�b�վ����᪺�ѽL��m
			endtWeight = startWeight + colNum*lattice;
			endHeight = starHeight + rowNum*lattice;
			
		}
		
	}
	
	public void setTableColor()   //�]�w����
	{
		tableColor = getResources().getColor(R.color.gray);
		
		
	}
	
	public void setChessRadiu()   //�]�w�Ѥl�b�|
	{
		circleRadiu = lattice/2;
	}
	
	public void setChess()      
	{
		circleLocX = new float[2][16];
		circleLocY = new float[2][16];
	}
	
	public void setTextSize()      //�]�w�r���j�p
	{
		textSize = (int)circleRadiu;
	}
	
	public void setPaint()        //�]�w�e��
	{
		paint_Chess = new Paint();
		paint_Chess.setColor(Color.WHITE);
		paint_Chess.setStrokeCap(Paint.Cap.ROUND);
		
		paint_BLACKText = new Paint();
		paint_BLACKText.setColor(getResources().getColor(R.color.black));
		paint_BLACKText.setTextSize(textSize);
		
		paint_REDText = new Paint();
		paint_REDText.setColor(getResources().getColor(R.color.red));
		paint_REDText.setTextSize(textSize);
		
		paint_Line = new Paint();
		paint_Line.setColor(getResources().getColor(R.color.Pink));
		paint_Line.setStrokeWidth(5);
		paint_Line.setStrokeCap(Paint.Cap.ROUND);
		
		Paint_ears = new Paint();
		Paint_ears.setColor(tableColor);
		Paint_ears.setStrokeWidth(5);
		
	}

	protected void onDraw(Canvas canvas)    //�}�l�G��
	{
		canvas.drawColor(tableColor);
		
		drawChessBoard(canvas);
		
		drawChess(canvas);
		
		
	}
	
	public void drawChessBoard(Canvas canvas)    //�e�ѽL
	{
		
		// �e��u
		for(int i=0 ;  i<=rowNum; i++ )
			canvas.drawLine(startWeight,starHeight+i*lattice,endtWeight,starHeight+i*lattice,paint_Line);
		
		// �e���u
		for(int i=0 ;  i<=colNum; i++ )
			canvas.drawLine(startWeight+i*lattice,starHeight,startWeight+i*lattice,endHeight,paint_Line);
		
		// �e�|���׽u
		canvas.drawLine(startWeight+3*lattice,starHeight,startWeight+5*lattice,starHeight+2*lattice,paint_Line);
		canvas.drawLine(startWeight+5*lattice,starHeight,startWeight+3*lattice,starHeight+2*lattice,paint_Line);
		canvas.drawLine(startWeight+3*lattice,starHeight+7*lattice,startWeight+5*lattice,starHeight+9*lattice,paint_Line);
		canvas.drawLine(startWeight+5*lattice,starHeight+7*lattice,startWeight+3*lattice,starHeight+9*lattice,paint_Line);
		
		// �]�w�e���ĪG�ܦ����l,i=1 && i<= colNum-1(���F�Ĥ@���M�̫�@���A�������n����)
		for(int i=1 ; i<= colNum-1 ; i++ )
			canvas.drawLine(startWeight+i*lattice,starHeight+lattice*4,startWeight+i*lattice,starHeight+lattice*5,Paint_ears);
		
	}
	
	public void drawChess(Canvas canvas)     //�e�Ѥl
	{
		
		for(int i=0 ; i < RedChess.length ; i++)
		{
			canvas.drawCircle(startWeight+RedChess[i].getXLoc()*lattice,
					starHeight+RedChess[i].getYLoc()*lattice,circleRadiu,paint_Chess);
			canvas.drawText(RedChess[i].getName(), startWeight+RedChess[i].getXLoc()*lattice-circleRadiu/2, 
					starHeight+RedChess[i].getYLoc()*lattice+circleRadiu/2, paint_REDText);
			circleLocX[RedChess[i].getColor()][i]= startWeight+RedChess[i].getXLoc()*lattice;
			circleLocY[RedChess[i].getColor()][i]= starHeight+RedChess[i].getYLoc()*lattice;
		}
		
		for(int i=0 ; i < BlackChess.length ; i++)
		{
			canvas.drawCircle(startWeight+BlackChess[i].getXLoc()*lattice,
					starHeight+BlackChess[i].getYLoc()*lattice,circleRadiu,paint_Chess);
			canvas.drawText(BlackChess[i].getName(), startWeight+BlackChess[i].getXLoc()*lattice-circleRadiu/2, 
					starHeight+BlackChess[i].getYLoc()*lattice+circleRadiu/2, paint_BLACKText);
			circleLocX[BlackChess[i].getColor()][i]= startWeight+BlackChess[i].getXLoc()*lattice;
			circleLocY[BlackChess[i].getColor()][i]= starHeight+BlackChess[i].getYLoc()*lattice;
		}
		
	}
	
	public void c_TouchDown(float touchX , float touchY)   //Ĳ�I--���U
	{
		touchPointX = touchX;
		touchPointY = touchY;
		IstouchDownCircle = false;
		
		int[] coordinate = new int[2];
		coordinate = absolTorelat(touchX,touchY);
		boolean search = true; 
		
		
		for(int i=0 ; i < RedChess.length && search == true; i++)
		{
			if(RedChess[i].getXLoc() == coordinate[0] 
					&& RedChess[i].getYLoc() == coordinate[1] )
			{
				Num=i;
				colorside = 0;
				search = false;
				IstouchDownCircle = true;
			}
			
			
		}	
		
		for(int i=0 ; i < BlackChess.length && search == true; i++)
		{
			if(BlackChess[i].getXLoc() == coordinate[0] 
					&& BlackChess[i].getYLoc() == coordinate[1] )
			{
				Num=i;
				colorside = 1;
				IstouchDownCircle = true;
			}
			
		}
		
		search = true;
		
	}
	
	public void c_TouchMove(float touchX , float touchY )  //Ĳ�I--����
	{
		
		if(IstouchDownCircle)
		{
			circleLocX[colorside][Num] = touchX ;
			circleLocY[colorside][Num] = touchY ;
			
		}
		
		touchPointX = touchX;
		touchPointY = touchY;
		
		invalidate();
		 
	}
	
	public void c_TouchUp(float touchX , float touchY)  //Ĳ�I--���}
	{
		
		if(IstouchDownCircle)
		{	
			int[] coordinate = new int[2];
			coordinate = absolTorelat(touchX ,touchY);          //�N���ܫ᪺�y�Цs�^
			if(checkLegitimate(coordinate[0],coordinate[1]))	//�T�{���I�O�_���T
			{
				if(colorside == 0)
				{	
					RedChess[Num].setXLoc(coordinate[0]);
					RedChess[Num].setYLoc(coordinate[1]);
				}else
				{
					BlackChess[Num].setXLoc(coordinate[0]);
					BlackChess[Num].setYLoc(coordinate[1]);
				}	
			}
		}
		IstouchDownCircle = false;
		invalidate(); 
		
		
	}
	
	public int[] absolTorelat(float touchX , float touchY)   //����y���ഫ���۹�y��
	{
		int[] coordinate = new int[2];
		
		float x = (touchX-startWeight)/lattice;
		float y = (touchY-starHeight)/lattice;
		
		if(x - (int)x >0.5)
		{
			coordinate[0]=(int)x+1;
		}else
			coordinate[0]=(int)x;
		
		if(y - (int)y >0.5)
		{
			coordinate[1]=(int)y+1;
		}else
			coordinate[1]=(int)y;
		
		
		return coordinate;
		
	}
	
	public boolean checkLegitimate(int X, int Y)  //�T�{�Ѥl��m���I�O�_���T
	{
		//��J�W�hclass
		return true;
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
