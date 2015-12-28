package com.example.gameproject;

public class Chess 
{
	private int color;
	private String name;
	private int code;
	private int xAxis;
	private int yAxis;
	
	public Chess(int color,String name,int code,int xAxis,int yAxis)
	{
		
		setChess(color,name,code,xAxis,yAxis);
		
	}
	
	public Chess(Chess chess)
	{
		setChess(chess.color,chess.name,chess.code,chess.xAxis,chess.yAxis);
	}
	
	public void setChess(int color,String name,int code,int xAxis,int yAxis)
	{
		
		this.color=color;
		this.name=name;
		this.code=code;
		this.xAxis=xAxis;
		this.yAxis=yAxis;
		
	}
	
	public void setChess(Chess chess)
	{
		this.color=chess.color;
		this.name=chess.name;
		this.code=chess.code;
		this.xAxis=chess.xAxis;
		this.yAxis=chess.yAxis;
		
	}

	public String getName() {
		
		return name;
	}
	
	public int getCode()
	{
		return code;
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
