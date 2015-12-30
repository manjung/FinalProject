package com.example.gameproject;

public class LongChessBridge 
{
	private LongChessControler LCC;
	private PlayerControl PC;
	private LongChessRegulation LCRG;
	private LongChessEatRegulation LCER;
	
	
	public void setLongChessControl(LongChessControler lcc)
	{
		this.LCC = lcc;
	}
	
	public void setPlayerControl(PlayerControl pc)
	{
		this.PC = pc;
	}
	 
	
	public boolean checkLocation(LongChessRecord longCR,Location loc,Chess chess)
	{
		
		if(PC.displaySide() != chess.getColor())
		{
			return false;
		}else
		{
			LCRG = new LongChessRegulation(longCR,loc,chess);
			
			boolean check = LCRG.checkDown();
			
			if(check)
			{
				PC.notifys();
			}
			
			return check;
		}	
		
		
	}
}
