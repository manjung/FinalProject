package com.example.gameproject;


public class Player 
{
	private String name;
	private int status;   
	private int side;
	
	public Player(String name,int side,int s)
	{
		setPlayer(name);
		setSide(side);
		setStatus(s);
	}
	
	public void setPlayer(String name)
	{
		this.name = name;
		
	}
	
	public void setStatus(int s)
	{
		this.status = s;
	}
	
	public void setSide(int s)
	{
		this.side = s;
	}
	
	public String getName()
	{
		return this.name;
		
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	public int getSide()
	{
		return this.side;
	}
	
	public Player getPlayer()
	{
		return new Player(name,status,side);
		
	}
}
