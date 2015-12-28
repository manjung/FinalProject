package com.example.gameproject;


public class LongChessMoveRegulation 
{
	private LongChessRecord longCR;
	private Chess Chess;
	private Location Loc;
	
	
	public LongChessMoveRegulation(LongChessRecord LCR,Location loc,Chess chess)
	{
		this.longCR=LCR;
		this.Loc = loc;
		this.Chess = chess;
		
	}
	
	public boolean CanMove()
	{
		
		if(Chess.getCode() == 1 || Chess.getCode() == 2 )	
		{
			if(moveNum(1,true))
			{
				if( Loc.getYLocation() - Chess.getYLoc() == 0            //只能走直線
						|| Loc.getXLocation() - Chess.getXLoc() == 0)
				{
					if(Loc.getYLocation() - Chess.getYLoc() == 0)        //確認前往目的地前沒有被擋到
					{
						int[] MinMax = MinMax(Loc.getXLocation(),Chess.getXLoc());
						return checkOutOfWay("Y",MinMax[0],MinMax[1],Loc.getYLocation());
						
						
					}else if(Loc.getXLocation() - Chess.getXLoc() == 0)
					{
						int[] MinMax = MinMax(Loc.getYLocation(),Chess.getYLoc());
						return checkOutOfWay("X",MinMax[0],MinMax[1],Loc.getXLocation());
					}
					
					return true;
					
				}
				
				
				
			}	
			
		}else if(Chess.getCode() == 3)
		{
			if(moveNum(3,false))
			{
				if( Loc.getYLocation() - Chess.getYLoc() == 0            //不能走直線
						|| Loc.getXLocation() - Chess.getXLoc() == 0)
				{
					
					return false;
				}else                                                     //是否有拐馬腳
				{
					return checkHourse(Chess.getXLoc(),Chess.getYLoc()
							,Loc.getXLocation(),Loc.getYLocation());
				}
				
			}
			
		}else if(Chess.getCode() == 4)	
		{
			if(moveNum(4,false))
			{
				if( Math.abs(Loc.getYLocation() - Chess.getYLoc()) == 2          //確認是否走田字           
						&& Math.abs(Loc.getXLocation() - Chess.getXLoc()) == 2)
				{
					
					return  checkElephant(Chess.getXLoc(),Chess.getYLoc()      //確認是否塞象眼
							,Loc.getXLocation(),Loc.getYLocation());
				}
				
			}
			
		}else if(Chess.getCode() == 5)	
		{
			if(moveNum(2,false))
			{
				if(Loc.getXLocation() >= 3 && Loc.getXLocation() <= 5 ) //不超出格數
						
				{	if( Loc.getYLocation() >= 0 && Loc.getYLocation() <=2
						|| Loc.getYLocation() >= 7&& Loc.getYLocation() <= 9)
					{
					
					   return true;
					}
					
					
				}
				
				
				
			}
			
		}else if(Chess.getCode() == 6)	
		{
			if(moveNum(1,false))
			{
				if(Loc.getXLocation() > 2 && Loc.getXLocation() < 6 ) //不超出格數
					
				{
					if( Loc.getYLocation() >= 0 && Loc.getYLocation() <=2
							|| Loc.getYLocation() >= 7&& Loc.getYLocation() <= 9)
						{
						
						   return true;
						}
				}
				
			}
			
		}else if(Chess.getCode() == 7)
		{
			if(moveNum(1,false))
			{
				if( Chess.getName() == "兵" && Loc.getYLocation() < 6 )  //未過河前，不得後退
				{
					if( Chess.getYLoc() == 5 && Loc.getYLocation() == 5 )  //未過河，不得平移
					{
						return false;
					}
					else if( Loc.getYLocation() - Chess.getYLoc() > 0)  //過河後，只前進、平移，不後退
					{
						return false;
					}else
						return true;
					
				}else if(Chess.getName() == "卒" && Loc.getYLocation() > 3)
				{
					if( Chess.getYLoc() == 4 && Loc.getYLocation() == 4 )  //未過河，不得平移
					{
						return false;
					}
					else if( Loc.getYLocation() - Chess.getYLoc() < 0)  //過河後，只前進、平移不後退
					{
						return false;
					}else
						return true;
					
				}
				
			}
				
		
		}
		return false;
	}
	
	
	private boolean moveNum(int num,boolean b)
	{
		if(Loc.getXLocation() > 8 || Loc.getYLocation() >9 || Loc.getXLocation() < 0  //不超出格數
				|| Loc.getYLocation() < 0)
		{
			return false;
		}else if((Math.abs(Chess.getXLoc()-Loc.getXLocation())
				+Math.abs(Chess.getYLoc()-Loc.getYLocation())) >= num && b==true)
		{
			return true;
		}
		else if((Math.abs(Chess.getXLoc()-Loc.getXLocation())
				+Math.abs(Chess.getYLoc()-Loc.getYLocation())) == num && b==false)
		{
			return true;
		}
		else
			return false;
	}
	
	private boolean checkOutOfWay(String XorY,int num1,int num2,int num)
	{
		int[][]chessboardColor = longCR.getChessboardColor();
		
		if(XorY.equals("X"))
		{
			for(int i=num1+1 ; i<num2 ; i++)	
			{
				if(chessboardColor[num][i] != -1)
				{
					return false;
				}
				
			}	
			
		}else
		{
			for(int i=num1+1 ; i<num2 ; i++)	
			{
				if(chessboardColor[i][num] != -1)
				{
					return false;
				}
				
			}
			
		}
		
		return true;
		
		
	}
	
	private boolean checkHourse(int startX,int startY,int endX,int endY)
	{
		int[][]chessboardColor = longCR.getChessboardColor();
		
		if(Math.abs(startX-endX)==1)
		{
			if( startX-endX==1 && startY-endY==2 || startX-endX==-1 && startY-endY==-2)	
			{
				int[] XMinMax = MinMax(startX,endX);
				int[] YMinMax = MinMax(startY,endY);
				
				if( (chessboardColor[XMinMax[0]+1][YMinMax[0]] != -1 || 
						chessboardColor[XMinMax[0]+1][YMinMax[0]+1] != -1)
						&& (chessboardColor[XMinMax[0]][YMinMax[0]+1] != -1 || 
						chessboardColor[XMinMax[0]][YMinMax[0]+2] != -1))
				{
					return false;
				}
				
			}else
			{
				if( startX < endX )  //交換
				{
					int temp;
					temp = startX;
					startX = endX;
					endX = temp;

					temp = startY;
					startY = endY;
					endY = temp;
				}
				if((chessboardColor[startX-1][startY] != -1 || 
						chessboardColor[startX-1][startY+1] != -1)
						&& (chessboardColor[startX][startY+1] != -1 || 
						chessboardColor[startX][startY+2] != -1))
				{
					return false;
					
				}
				
			}	
			
		}else
		{
			if( startX-endX==2 && startY-endY==1 || startX-endX==-2 && startY-endY==-1)	
			{
				int[] XMinMax = MinMax(startX,endX);
				int[] YMinMax = MinMax(startY,endY);
				
				
				if( (chessboardColor[XMinMax[0]+1][YMinMax[0]] != -1 || 
						chessboardColor[XMinMax[0]+2][YMinMax[0]] != -1)
						&& (chessboardColor[XMinMax[0]][YMinMax[0]+1] != -1 || 
						chessboardColor[XMinMax[0]+1][YMinMax[0]+1] != -1))
				{
					return false;
				}
				
			}else
			{
				if( startX < endX )  //交換
				{
					int temp;
					temp = startX;
					startX = endX;
					endX = temp;

					temp = startY;
					startY = endY;
					endY = temp;
				}
				if((chessboardColor[startX-1][startY] != -1 || 
						chessboardColor[startX-2][startY] != -1)
						&& (chessboardColor[startX][startY+1] != -1 || 
						chessboardColor[startX-1][startY-1] != -1))
				{
					return false;
					
				}
				
			}	
			
		}	
		
		return true;
		
	}
	
	private boolean checkElephant(int startX,int startY,int endX,int endY)
	{
		int[][]chessboardColor = longCR.getChessboardColor();
		
		if( startX-endX==2 && startY-endY==2 || startX-endX==-2 && startY-endY==-2)	
		{
			int[] XMinMax = MinMax(startX,endX);
			int[] YMinMax = MinMax(startY,endY);
			
			if( chessboardColor[XMinMax[0]+1][YMinMax[0]+1] != -1 )
			{
				return false;
			}	
			
		}else
		{	
			if( startX < endX )  //交換
			{
				int temp;
				temp = startX;
				startX = endX;
				endX = temp;

				temp = startY;
				startY = endY;
				endY = temp;
			}
			
			if( chessboardColor[startX-1][startY+1] != -1 )
			{
				return false;
			}	
			
		}	
		
		return true;
	}
	
	private int[] MinMax(int num1,int num2)
	{
		int[] num = new int[2];
		
		if(num1>num2)
		{
			num[0] = num2;
			num[1] = num1;
		}else
		{
			num[0] = num1;
			num[1] = num2;
		}
		
		
		return num;
	}
	
	
}
