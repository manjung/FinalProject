package com.example.gameproject;


public class Player 
{
	private String name ;
	
	public Player(String name)
	{
		setPlayer(name);
	}
	
	public void setPlayer(String name)
	{
		this.name = name;
		
	}
	
	public Player getPlayer(String name)
	{
		return new Player(name);
		
	}
}
