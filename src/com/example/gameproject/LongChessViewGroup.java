package com.example.gameproject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

public class LongChessViewGroup extends ViewGroup 
{
	
	public LongChessViewGroup(Context context) {
		super(context);
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		 int widthSize = MeasureSpec.getSize(widthMeasureSpec);  
		 int heightSize = MeasureSpec.getSize(heightMeasureSpec);  
		  
		 measureChildren(widthMeasureSpec, heightMeasureSpec);     
		      
		 setMeasuredDimension(widthSize, heightSize);  
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) 
	{
		// TODO Auto-generated method stub
		
		 int mTotalHeight = 0;  
	      
	       // 遍歷所有子視圖     
	    int childCount = getChildCount();  
	    for (int i = 0; i < childCount; i++) {  
	        View childView = getChildAt(i);  
	          
	        // 獲取在onMeasure中計算的視圖尺寸  
	        int measureHeight = childView.getMeasuredHeight();  
	        int measuredWidth = childView.getMeasuredWidth();  
	        
	        left = (right-measuredWidth)/2;
	          
	        childView.layout(left, mTotalHeight, measuredWidth, mTotalHeight + measureHeight);  
	          
	        mTotalHeight += measureHeight;  
	    }
	          
	        
	}
	 

}
