package edu.buu.czyc;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class ViewWelcome {
	private Bitmap bitmap = null;
	private Rect bitmapSrcRect , bitmapDesRect;
	private Resources res; 
	private boolean is_display = false; 
	private int screenW ;
	private int screenH ;
	public void set_is_display(boolean is_display)
	{
		this.is_display = is_display;
	}
	public boolean get_is_display()
	{
		return is_display;
	}
	
	public void againstOOM(boolean ini_flag)
	{
		if (ini_flag)
		{//∑÷≈‰
			bitmap = BitmapFactory.decodeResource(res, R.drawable.welcomview);
			int BitWidth = bitmap.getWidth();
	        int BitHeight = bitmap.getHeight();
	        bitmapSrcRect = new Rect(0, 0, BitWidth, BitHeight);
			bitmapDesRect = new Rect(0, 0, screenW, screenH);
		}
		else
		{// Õ∑≈
			if (bitmap!=null)
			{
				bitmap.recycle();
			}
			bitmap = null;
		}
	}
	
	public ViewWelcome(Resources res,int screenW,int screenH) 
	{
		this.res = res;  
		this.screenW = screenW;
		this.screenH = screenH;
	}

	//ªÊ÷∆
	public void draw(Canvas canvas, Paint paint)
	{
		if (is_display)
		{
			if (bitmap==null)
			{
				againstOOM(true);
			}
			canvas.drawBitmap(bitmap, bitmapSrcRect, bitmapDesRect, null);
		}
		else
		{
			againstOOM(false);
		}
	}
}

