package com.example.gameproject;

public class Chess 
{
	private int color;
	private String name;
	private int code;
	private int xAxis;
	private int yAxis;
	private boolean status;
	private boolean coverup;
	
	
	public Chess(int color,String name,int code,int xAxis,int yAxis,boolean status)
	{
		
		setChess(color,name,code,xAxis,yAxis,status);
		
	}
	
	public Chess(int color,String name,int code,int xAxis,int yAxis,boolean status,boolean coverup)
	{
		
		setChess(color,name,code,xAxis,yAxis,status,coverup);
		
	}
	
	public Chess(Chess chess)
	{
		setChess(chess.color,chess.name,chess.code,chess.xAxis,chess.yAxis,chess.status);
	}
	
	public Chess(Chess chess,boolean coverup)
	{
		setChess(chess.color,chess.name,chess.code,chess.xAxis,chess.yAxis,chess.status,coverup);
	}
	
	public void setChess(int color,String name,int code,int xAxis,int yAxis,boolean status)
	{
		
		this.color=color;
		this.name=name;
		this.code=code;
		this.xAxis=xAxis;
		this.yAxis=yAxis;
		this.status=status;
		
	}
	public void setChess(int color,String name,int code,int xAxis,int yAxis,
			boolean status,boolean coverup)
	{
		this.color=color;
		this.name=name;
		this.code=code;
		this.xAxis=xAxis;
		this.yAxis=yAxis;
		this.status=status;
		this.coverup= coverup;
	}
	
	public void setChess(Chess chess)
	{
		
		this.color=chess.color;
		this.name=chess.name;
		this.code=chess.code;
		this.xAxis=chess.xAxis;
		this.yAxis=chess.yAxis;
		this.status=chess.status;
	}

	public void setChess(Chess chess,boolean coverup)
	{
		
		this.color=chess.color;
		this.name=chess.name;
		this.code=chess.code;
		this.xAxis=chess.xAxis;
		this.yAxis=chess.yAxis;
		this.status=chess.status;
		this.coverup= coverup;
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public boolean getStatus()
	{
		return status;
	}

	public void setXLoc(int xAxis)
	{
		this.xAxis=xAxis;
	}	
	
	public void setYLoc(int yAxis)
	{
		this.yAxis=yAxis;
	}
	
	public void setStatus(boolean s)
	{
		this.status=s;
	}
	
	public void setCoverup(boolean c)
	{
		this.coverup=c;
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
	
	public boolean getCoverUp()
	{
		return coverup;
	}
	

}
