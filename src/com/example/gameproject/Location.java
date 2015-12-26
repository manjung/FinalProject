package com.example.gameproject;

public class Location 
{
	
	private int xLocation;
	private int yLocation;
	
	public Location(int x, int y)
	{
		if(CheckLocation(x,y))
		{
			setLocation(x,y);
			
		}else
		{
			System.exit(0);
		}	
		
	}
	
	
	public Location()
	{
		
		
	}
	
	public void setLocation(int x, int y)
	{
		this.xLocation = x;
		this.yLocation = y;
	}
	
	public void setXLocation(int x)
	{
		this.xLocation = x;
	}
	
	public void setYLocation(int y)
	{
		this.yLocation = y;
	}
	
	public int getXLocation()
	{
		return this.xLocation;
	}
	
	public int getYLocation()
	{
		return this.yLocation;
	}
	
	public boolean CheckLocation(int x,int y)
	{
		return true;
		
	}

}
