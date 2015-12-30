package com.example.gameproject;

import android.util.Log;

public class LongChessEatRegulation 
{	
	private LongChessRecord longCR;
	private Chess Chess;
	private Chess EatChess;    //被吃的棋子
	private Location Loc;
	
	private static int BLACK = 1;
	private static int RED = 0;
	
	//如果有棋子死亡記得紀錄！
	
	public LongChessEatRegulation(LongChessRecord LCR,Location loc,Chess chess)
	{
		this.longCR = LCR;
		this.Loc = loc;
		this.Chess = chess;
		
	}
	
	public boolean setEatChess()
	{
		
		if(longCR.getChess(Loc.getXLocation(), Loc.getYLocation()).getColor() != -1)
		{
			EatChess = longCR.getChess(Loc.getXLocation(), Loc.getYLocation());
			Log.v("EatChess", EatChess.toString());
			return true;
		}else
			return false;
		
	}
	
	public boolean CanEat()
	{
		if(setEatChess())
		{
		
			if(Chess.getCode() == 1)
			{
				if(Loc.getYLocation() - Chess.getYLoc() == 0)
				{
					int[] MinMax = MinMax(Loc.getXLocation(),Chess.getXLoc());
					return checkOneinWay("Y",MinMax[0],MinMax[1],Loc.getYLocation());
				
				}else
				{
					int[] MinMax = MinMax(Loc.getYLocation(),Chess.getYLoc());
					return checkOneinWay("X",MinMax[0],MinMax[1],Loc.getXLocation());
				}	
			
			}else 
			{
				EatChess.setStatus(false);
				longCR.setChangeChess(EatChess);
				return true;
			}
		
		}else
		   return false;	
		
	}
	
	private boolean checkOneinWay(String XorY,int num1,int num2,int num)
	{
		int[][]chessboardColor = longCR.getChessboardColor();
		
		int count = 0;
		
		if(XorY.equals("X"))
		{
			for(int i=num1+1 ; i<num2 ; i++)	
			{
				if(chessboardColor[num][i] != -1)
				{
					count++;
				}
				
			}	
			
		}else
		{
			for(int i=num1+1 ; i<num2 ; i++)	
			{
				if(chessboardColor[i][num] != -1)
				{
					count++;
				}
				
			}
			
		}
		
		if(count == 1)
		{
			EatChess.setStatus(false);
			longCR.setChangeChess(EatChess);
			return true;
		}
		else 
			return false;
	}
	
	private int[] MinMax(int num1,int num2)
	{
		int[] num = new int[2];
		
		if(num1>num2)
		{
			num[0] = num2;
			num[1] = num1;
		}else
		{
			num[0] = num1;
			num[1] = num2;
		}
		
		
		return num;
	}


}
