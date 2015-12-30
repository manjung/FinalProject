package com.example.gameproject;

public class PlayerControl 
{
	private PlayerRecord   playerRecord;
	private PlayerView     playerView;
	private Player	       playerRed;
	private Player	       playerBlack;
	
	private static int ON = 0;
	private static int WAIT = 1;
	private static int BLACK = 1;
	private static int RED = 0;
	
	public PlayerControl(PlayerRecord pR,PlayerView pV)
	{
		this.playerRecord = pR;
		this.playerView = pV;
		
		inital();
	}
	
	public void inital()
	{
		this.playerRed = playerRecord.getPlayerRed();
		this.playerBlack = playerRecord.getPlayerBlack();
	}
	
	public void notifys()
	{
		if(displaySide() == RED)
		{
			playerRed.setStatus(WAIT);
			playerBlack.setStatus(ON);
		
		}else if(displaySide() == BLACK)
		{
			playerRed.setStatus(ON);
			playerBlack.setStatus(WAIT);
			
		}
		
	}
	
	public int displaySide()
	{
		if(playerRed.getStatus() == ON)
		{
			return playerRed.getSide();
		}else
		{
			return playerBlack.getSide();
		}	
		
	}
	
	
	
	
}
