package com.example.gameproject;

import android.util.Log;

public class LongChessRecord 
{
	private Chess[] BlackChess;
	private Chess[] RedChess;
	private int[][] chessboardNum;
	private int[][] chessboardColor;
	private String[][] chessboardName;
	private int rowNum;
	private int columNum;
	
	public LongChessRecord(Chess[] Black,Chess[] Red,int row,int colum)
	{
		this.BlackChess = Black;
		this.RedChess = Red;
		this.rowNum=row;
		this.columNum=colum;
		setChessBoard(rowNum,columNum);
	}
	
	public void setChessBoard(int row,int col)
	{
		chessboardNum = new int[row][col];
		chessboardColor = new int[row][col];
		chessboardName = new String[row][col];
		
		for(int i=0 ; i<RedChess.length ; i++)
		{	
			chessboardNum[RedChess[i].getXLoc()][RedChess[i].getYLoc()] 
					= RedChess[i].getCode();
			chessboardColor[RedChess[i].getXLoc()][RedChess[i].getYLoc()] 
					= RedChess[i].getColor();
			chessboardName[RedChess[i].getXLoc()][RedChess[i].getYLoc()] 
					= RedChess[i].getName();
			
		}
		
		for(int i=0 ; i<BlackChess.length ; i++)
		{
			chessboardNum[BlackChess[i].getXLoc()][BlackChess[i].getYLoc()] 
					= BlackChess[i].getCode();
			chessboardColor[BlackChess[i].getXLoc()][BlackChess[i].getYLoc()] 
					= BlackChess[i].getColor();
			chessboardName[BlackChess[i].getXLoc()][BlackChess[i].getYLoc()] 
					= BlackChess[i].getName();
		}
		
		for(int i=0 ;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(chessboardColor[i][j]==0 && chessboardNum[i][j] ==0)
				{
					chessboardColor[i][j] = -1;
				}	
				
			}
			
		}
		
	}
	
	public void setChangeChess(Chess newChess,int colorside,int Num)
	{
		 
		 if(colorside == 0)
		 {
			 RedChess[Num].setChess(newChess);
		 }
		 else
		 {
			 BlackChess[Num].setChess(newChess);
			 
		 } 
		
		 setChessBoard(rowNum,columNum);
		
	}
	
	public Chess[] getBlackChess()
	{
		return BlackChess;
	}
	
	public Chess[] getRedChess()
	{
		return RedChess;
	}
	
	public int[][] getChessboardNum()
	{
		return chessboardNum;
	}
	
	public int[][] getChessboardColor()
	{
		return chessboardColor;
	}
	
	public String[][] getChessboardName()
	{
		return chessboardName;
	}
	
	
	
}
