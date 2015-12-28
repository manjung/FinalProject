package com.example.gameproject;

import android.util.Log;

public class LongChessControler 
{
	private boolean IstouchDownCircle;      //�w���U�� 
	private int Num;                        //�������U�����Ѥl
	private int colorside;                  //�����{�b�O���@��U��
	private Location Loc;                   //������m
	private Chess[] BlackChess;
	private Chess[] RedChess;
	private LongChessRecord           longCR              ;
	private LongChessBoardView        chessBoard 	      ;
	private float startWeight;
	private float starHeight;
	private int lattice;
	
	public LongChessControler(LongChessRecord longCR,LongChessBoardView chessBoard )
	{
		this.longCR = longCR ;
		this.chessBoard = chessBoard;
		inital();
	}
	
	public void inital()
	{
		this.BlackChess = longCR.getBlackChess();
		this.RedChess = longCR.getRedChess();
		
		
	}
	
	public void set()
	{
		this.startWeight = chessBoard.getStartWeight();
		this.starHeight = chessBoard.getStarHeight();
		this.lattice = chessBoard.getLattice();
	}
	
	
	
	public void c_TouchDown(float touchX , float touchY)   //Ĳ�I--���U
	{
		
		IstouchDownCircle = false;
		
		absolTorelat(touchX,touchY);
		Log.v("Loc.getXLocation()", Integer.toString(Loc.getXLocation()));
		Log.v("Loc.getYLocation()", Integer.toString(Loc.getYLocation()));
		boolean search = true; 
		
		for(int i=0 ; i < RedChess.length && search == true; i++)
		{
			if(RedChess[i].getXLoc() == Loc.getXLocation() 
					&& RedChess[i].getYLoc() == Loc.getYLocation() )
			{
				Num=i;
				colorside = 0;
				search = false;
				IstouchDownCircle = true;
			}
			
			
		}	
		
		for(int i=0 ; i < BlackChess.length && search == true; i++)
		{
			
			if(BlackChess[i].getXLoc() == Loc.getXLocation() 
					&& BlackChess[i].getYLoc() == Loc.getYLocation() )
			{
				Num=i;
				colorside = 1;
				search = false;
				IstouchDownCircle = true;
			}
			
		}
		
		search = true;
		
	}
	
	public void c_TouchMove(float touchX , float touchY )  //Ĳ�I--����
	{
		
		
		 
	}
	
	public void c_TouchUp(float touchX , float touchY)  //Ĳ�I--���}
	{
		
		if(IstouchDownCircle)
		{	
			absolTorelat(touchX ,touchY);        //�N���ܫ᪺�y�Цs�^
			if(checkLocation())                  //�T�{���I�O�_���T
			{
				Chess newChess;
				
				if(colorside == 0)
				{	
					
					RedChess[Num].setXLoc(Loc.getXLocation());
					RedChess[Num].setYLoc(Loc.getYLocation());
					newChess = RedChess[Num];
					
				}else
				{
					
					BlackChess[Num].setXLoc(Loc.getXLocation());
					BlackChess[Num].setYLoc(Loc.getYLocation());
					newChess = BlackChess[Num];
				}
				
				notify(newChess,colorside,Num);
				
			}
		}
		
		IstouchDownCircle = false;
		
		
	}
	
	public void notify(Chess newChess,int colorside,int Num)
	{
		
		longCR.setChangeChess(newChess,colorside,Num);
		
	}
	
	
	public void absolTorelat(float touchX , float touchY)   //����y���ഫ���۹�y��
	{
		
		float x = (touchX-startWeight)/lattice;
		float y = (touchY-starHeight)/lattice;
		
		Loc = new Location();
		
		if(x - (int)x >0.5)
		{
			Loc.setXLocation((int)x+1);
		}else
			
			Loc.setXLocation((int)x);
		
		if(y - (int)y >0.5)
		{
			Loc.setYLocation((int)y+1);
		}else
			
		    Loc.setYLocation((int)y);
		
	}
	
	public boolean checkLocation()
	{
		Chess chess;
		LongChessRecord longCR= new LongChessRecord(BlackChess,RedChess,9,10);
		
		if(colorside == 0)
			chess = new Chess(RedChess[Num]);
		else
			chess = new Chess(BlackChess[Num]);
		
		LongChessMoveRegulation longChess = new LongChessMoveRegulation(longCR,Loc,chess);
		
		
		return longChess.CanMove();
	}
	
}
