package com.example.gameproject;

import android.util.Log;

public class LongChessRegulation 
{
	private PlayerRecord PR;
	private LongChessRecord LCR;
	private Chess chess;
	private Location Loc;
	
	
	public LongChessRegulation(LongChessRecord longCR,Location loc,Chess chess)
	{
		this.LCR = longCR;
		this.Loc = loc;
		this.chess = chess;
		
	}
	
	public boolean checkDown()
	{
		int[][] color = LCR.getChessboardColor();
		
		
		
		if(Loc.getXLocation() > 8 && Loc.getYLocation() > 9 && Loc.getXLocation() < 0  //不超出格數
				&& Loc.getYLocation() < 0)
		{
			
			return false;
		
		}else
		{
			
			if( color[Loc.getXLocation()][Loc.getYLocation()] == chess.getColor() )
			{
				return false;
			}else
			{	
				LongChessMoveRegulation lcr = new LongChessMoveRegulation(LCR,Loc,chess);
				
				if(lcr.CanMove())
				{
					if(color[Loc.getXLocation()][Loc.getYLocation()]!=-1)
					{
						LongChessEatRegulation lcer = new LongChessEatRegulation(LCR,Loc,chess);
						return lcer.CanEat();
					
					}else
						return true;
				
				}else
				{
					if(chess.getCode() == 1)  //代表是包、炮
					{
						LongChessEatRegulation lcer = new LongChessEatRegulation(LCR,Loc,chess);
						
						return lcer.CanEat();
						
						
					}else
						return false;
				}	
		}	
				
		}	
		
	}
}