package edu.buu.czyc;

import java.util.ArrayList;
import java.util.List;

import edu.buu.czyc.R;
import edu.buu.czyc.R.drawable;
import edu.buu.czyc.conf.CallCarInfor;
import edu.buu.czyc.conf.ViewCallCar_FollowConfig;
import edu.buu.czyc.conf.ViewCarConfig;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ViewCallCar_Follow {
	private int screenW ;
	private int screenH ;
	Resources res;
	private Bitmap bitmap_tip1,bitmap_tip2;
	
	Bitmap_tip1 myBitmap_tip1;
	Bitmap_tip2 myBitmap_tip2;
	
	
	private Rect bitmapSrcRect_tip , bitmapDesRect_tip1,bitmapDesRect_tip2;
	
	public ViewCallCar_Follow(Resources res, int screenW ,int screenH) 
	{
		this.res = res;
		
		this.screenW = screenW;
		this.screenH = screenH;
		
	}
	
	public void againstOOM(boolean ini_flag)
	{
		if (ini_flag)
		{
			myBitmap_tip1 = new Bitmap_tip1(res);
			myBitmap_tip2 = new Bitmap_tip2(res);
			
			bitmap_tip1 = BitmapFactory.decodeResource(res, R.drawable.temp_tip1);
			bitmap_tip2 = BitmapFactory.decodeResource(res, R.drawable.temp_tip2 );
			
			bitmapSrcRect_tip = new Rect(0, 0, bitmap_tip1.getWidth(), bitmap_tip1.getHeight()) ;
			
			bitmapDesRect_tip1  =  calcRect(ViewCallCar_FollowConfig.view_tip1_start_x ,
					ViewCallCar_FollowConfig.view_tip1_start_y,
					ViewCallCar_FollowConfig.view_tip1_end_x,
					ViewCallCar_FollowConfig.view_tip1_end_y
							); 
			bitmapDesRect_tip2  =  calcRect(ViewCallCar_FollowConfig.view_tip2_start_x ,
					ViewCallCar_FollowConfig.view_tip2_start_y,
					ViewCallCar_FollowConfig.view_tip2_end_x,
					ViewCallCar_FollowConfig.view_tip2_end_y
							); 
		}
		else
		{// Õ∑≈
			if (myBitmap_tip1!=null)
				myBitmap_tip1.recycle();
			myBitmap_tip1 = null;
			
			if (myBitmap_tip2!=null)
				myBitmap_tip2.recycle();
			myBitmap_tip2 = null;
			
			
			if (bitmap_tip1!=null)
			{
				bitmap_tip1.recycle();
			}
			bitmap_tip1 = null;
			if (bitmap_tip2!=null)
			{
				bitmap_tip2.recycle();
			}
			bitmap_tip2 = null;
		}
	}
	public Rect calcRect(int x1,int y1,int x2,int y2)
	{
		return new Rect((int)((float)x1/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y1/(float)ViewCarConfig.sign_h*(float)screenH),
				(int)((float)x2/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y2/(float)ViewCarConfig.sign_h*(float)screenH));
	}
	//ªÊ÷∆
	public void draw(Canvas canvas, Paint paint)
	{ 
		if (CallCarInfor.show )
		{
			Bitmap temp = myBitmap_tip1.getBitmap();
			bitmapSrcRect_tip = new Rect(0, 0, temp.getWidth(), temp.getHeight()) ;
			canvas.drawBitmap(temp, bitmapSrcRect_tip, bitmapDesRect_tip1, null);
		}
		else
		{
			canvas.drawBitmap(bitmap_tip1, bitmapSrcRect_tip, bitmapDesRect_tip1, null);
		}
		
//		canvas.drawBitmap(bitmap_tip2, bitmapSrcRect_tip, bitmapDesRect_tip2, null);
		
		Bitmap temp2 = myBitmap_tip2.getBitmap();
		bitmapSrcRect_tip = new Rect(0, 0, temp2.getWidth(), temp2.getHeight()) ;
		canvas.drawBitmap(temp2, bitmapSrcRect_tip, bitmapDesRect_tip2, null);
	}
}

