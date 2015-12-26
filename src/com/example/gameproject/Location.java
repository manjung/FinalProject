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
	
	private void setLocation(int x, int y)
	{
		this.xLocation = x;
		this.yLocation = y;
	}
	
	public boolean CheckLocation(int x,int y)
	{
		return true;
		
	}

}
