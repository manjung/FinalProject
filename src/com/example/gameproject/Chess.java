package com.example.gameproject;

public class Chess 
{
	private int color;
	private String name;
	private int xAxis;
	private int yAxis;
	
	public Chess(int color,String name,int xAxis,int yAxis)
	{
		
		setChess(color,name,xAxis,yAxis);
		
	}
	
	public Chess(Chess chess)
	{
		setChess(chess.color,chess.name,chess.xAxis,chess.yAxis);
	}
	
	public void setChess(int color,String name,int xAxis,int yAxis)
	{
		
		this.color=color;
		this.name=name;
		this.xAxis=xAxis;
		this.yAxis=yAxis;
		
	}

	public String getName() {
		
		return name;
	}

	public void setXLoc(int xAxis)
	{
		this.xAxis=xAxis;
	}	
	
	public void setYLoc(int yAxis)
	{
		this.yAxis=yAxis;
	}
	
	public int getXLoc()
	{
		return xAxis;
	}
	
	public int getYLoc()
	{
		return yAxis;
	}
	
	public int getColor()
	{
		
		return color;
	}
	

}
