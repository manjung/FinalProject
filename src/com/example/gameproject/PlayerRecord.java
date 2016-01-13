package com.example.gameproject;

public class PlayerRecord 
{
	private Player	playerRed     ;
	private Player	playerBlack   ;
	
	public PlayerRecord(Player pRed,Player pBlack)
	{
		this.playerRed = pRed;
		this.playerBlack = pBlack;
	}
	
	public void setPlayerRed(Player pRed)
	{
		this.playerRed = pRed;
	}
	
	public void setPlayerBlack(Player pBlack)
	{
		this.playerBlack = pBlack;
	}
	
	public Player getPlayerRed()
	{
		return playerRed;
	}
	
	public Player getPlayerBlack()
	{
		return playerBlack;
	}
}
