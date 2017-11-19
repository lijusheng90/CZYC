package edu.buu.czyc;

import edu.buu.czyc.conf.ViewCarConfig;
import edu.buu.czyc.conf.ViewSettingConfig;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ViewSetting {
	private Bitmap bitmap;
	private Rect bitmapSrcRect , bitmapDesRect;
	private int screenW ;
	private int screenH ;
	private ViewXCZYC myViewXCZYC;
	
	private boolean bitmap_style1_isPress = false;
	private Bitmap bitmap_style_s;
	private Rect bitmapSrcRect_style_s , bitmapDesRect_style1_s;
	private boolean bitmap_style2_isPress = false;
	private Rect   bitmapDesRect_style2_s;
	private boolean bitmap_style3_isPress = false;
	private Rect   bitmapDesRect_style3_s;
	
	private boolean bitmap_confirm_isPress = false;
	private Bitmap bitmap_style1_confirm, bitmap_style1_confirm_s;
	private Bitmap bitmap_style2_confirm, bitmap_style2_confirm_s;
	private Bitmap bitmap_style3_confirm, bitmap_style3_confirm_s;
	private Rect bitmapSrcRect_confirm_s , bitmapDesRect_confirm_s;
	private Resources res; 
	public ViewSetting(ViewXCZYC myViewXCZYC,Resources res,int screenW,int screenH) 
	{
		this.res = res;
		this.myViewXCZYC = myViewXCZYC;
		this.screenW = screenW;
		this.screenH = screenH;
	}
	public Rect calcRect(int x1,int y1,int x2,int y2)
	{
		return new Rect((int)((float)x1/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y1/(float)ViewCarConfig.sign_h*(float)screenH),
				(int)((float)x2/(float)ViewCarConfig.sign_w*(float)screenW),
				(int)((float)y2/(float)ViewCarConfig.sign_h*(float)screenH));
	}
	public void set_is_display(boolean is_display)
	{
		this.is_display = is_display;
	}
	public boolean get_is_display()
	{
		return this.is_display;
	}
	
	//
	public void against(boolean ini_flag)
	{
		if (ini_flag)
		{//分配
			this.bitmap = BitmapFactory.decodeResource(res, R.drawable.viewsetting);;
			int BitWidth = bitmap.getWidth();
	        int BitHeight = bitmap.getHeight();
			
			bitmapSrcRect = new Rect(0, 0, BitWidth, BitHeight);
			bitmapDesRect = new Rect(0, 0, screenW, screenH);
			
			bitmap_style_s = BitmapFactory.decodeResource(res, R.drawable.viewcar_style_s);
			bitmapSrcRect_style_s = new Rect(0, 0, bitmap_style_s.getWidth(), bitmap_style_s.getHeight()) ;
			bitmapDesRect_style1_s = calcRect(ViewSettingConfig.view_style1_start_x ,
					ViewSettingConfig.view_style1_start_y,
					ViewSettingConfig.view_style1_end_x,
					ViewSettingConfig.view_style1_end_y
					); 
			
			bitmapDesRect_style2_s = calcRect(ViewSettingConfig.view_style2_start_x ,
					ViewSettingConfig.view_style2_start_y,
					ViewSettingConfig.view_style2_end_x,
					ViewSettingConfig.view_style2_end_y
					); 
			
			bitmapDesRect_style3_s = calcRect(ViewSettingConfig.view_style3_start_x ,
					ViewSettingConfig.view_style3_start_y,
					ViewSettingConfig.view_style3_end_x,
					ViewSettingConfig.view_style3_end_y
					); 
			
			
			bitmap_style1_confirm_s = BitmapFactory.decodeResource(res, R.drawable.view_car_style1_confirm_s);
			bitmap_style1_confirm = BitmapFactory.decodeResource(res, R.drawable.view_car_style1_confirm);
			bitmap_style2_confirm_s = BitmapFactory.decodeResource(res, R.drawable.view_car_style2_confirm_s);
			bitmap_style2_confirm = BitmapFactory.decodeResource(res, R.drawable.view_car_style2_confirm);
			bitmap_style3_confirm_s = BitmapFactory.decodeResource(res, R.drawable.view_car_style3_confirm_s);
			bitmap_style3_confirm = BitmapFactory.decodeResource(res, R.drawable.view_car_style3_confirm);

			bitmapSrcRect_confirm_s = new Rect(0, 0, bitmap_style1_confirm.getWidth(), bitmap_style1_confirm.getHeight()) ;
			bitmapDesRect_confirm_s = calcRect(ViewSettingConfig.view_style_confirm_start_x ,
					ViewSettingConfig.view_style_confirm_start_y,
					ViewSettingConfig.view_style_confirm_end_x,
					ViewSettingConfig.view_style_confirm_end_y
					); 
			
		}
		else
		{//释放
			if (bitmap!=null)
			{
				bitmap.recycle();
			}
			bitmap = null;

			if (bitmap_style_s!=null)
			{
				bitmap_style_s.recycle();
			}
			bitmap_style_s = null;
			
			if (bitmap_style1_confirm!=null)
			{
				bitmap_style1_confirm.recycle();
			}
			bitmap_style1_confirm = null;
			if (bitmap_style1_confirm_s!=null)
			{
				bitmap_style1_confirm_s.recycle();
			}
			bitmap_style1_confirm_s = null;
			
			if (bitmap_style2_confirm!=null)
			{
				bitmap_style2_confirm.recycle();
			}
			bitmap_style2_confirm = null;
			if (bitmap_style2_confirm_s!=null)
			{
				bitmap_style2_confirm_s.recycle();
			}
			bitmap_style2_confirm_s = null;
			
			
			if (bitmap_style3_confirm!=null)
			{
				bitmap_style3_confirm.recycle();
			}
			bitmap_style3_confirm = null;
			if (bitmap_style3_confirm_s!=null)
			{
				bitmap_style3_confirm_s.recycle();
			}
			bitmap_style3_confirm_s = null;
		}
	}
	private boolean is_display; 
	//绘制
	public void draw(Canvas canvas, Paint paint)
	{
		if (is_display)
		{
			if (bitmap==null)
			{
				against(true);
			}
			canvas.drawBitmap(bitmap, bitmapSrcRect, bitmapDesRect, null);
			
			if (myViewXCZYC.selectstyle==1)
			{
				canvas.drawBitmap(bitmap_style_s, bitmapSrcRect_style_s, bitmapDesRect_style1_s, null);
			}
			if  (myViewXCZYC.selectstyle==2)
			{
				canvas.drawBitmap(bitmap_style_s, bitmapSrcRect_style_s, bitmapDesRect_style2_s, null);
			}
			if  (myViewXCZYC.selectstyle==3)
			{
				canvas.drawBitmap(bitmap_style_s, bitmapSrcRect_style_s, bitmapDesRect_style3_s, null);
			}
			
			if (bitmap_confirm_isPress)
			{
				if (myViewXCZYC.selectstyle==1)
					canvas.drawBitmap(bitmap_style1_confirm_s, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
				if (myViewXCZYC.selectstyle==2)
					canvas.drawBitmap(bitmap_style2_confirm_s, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
				if (myViewXCZYC.selectstyle==3)
					canvas.drawBitmap(bitmap_style3_confirm_s, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
			}
			else
			{
				if (myViewXCZYC.selectstyle==1)
					canvas.drawBitmap(bitmap_style1_confirm, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
				if (myViewXCZYC.selectstyle==2)
					canvas.drawBitmap(bitmap_style2_confirm, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
				if (myViewXCZYC.selectstyle==3)
					canvas.drawBitmap(bitmap_style3_confirm, bitmapSrcRect_confirm_s, bitmapDesRect_confirm_s, null);
			}
		}
		else
		{
			against(false);
		}
	}
	
	//菜单触屏事件函数，主要用于处理按钮事件
	public boolean onTouchEvent(MotionEvent event) 
	{
		//获取用户当前触屏位置
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();
		
		switch (event.getAction() )
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				//判定用户是否点击了按钮
				if ( bitmapDesRect_style1_s.contains(pointX, pointY) ) 
				{
					bitmap_style1_isPress = true;
				} 
				else 
				{
					bitmap_style1_isPress = false;
				}
				
				if ( bitmapDesRect_style2_s.contains(pointX, pointY) ) 
				{
					bitmap_style2_isPress = true;
				} 
				else 
				{
					bitmap_style2_isPress = false;
				}
				
				if ( bitmapDesRect_style3_s.contains(pointX, pointY) ) 
				{
					bitmap_style3_isPress = true;
				} 
				else 
				{
					bitmap_style3_isPress = false;
				}
					if ( bitmapDesRect_confirm_s.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						bitmap_confirm_isPress = true;
					}
					else
					{
						bitmap_confirm_isPress = false;
					}
				break;
				//当用户是抬起动作
			case MotionEvent.ACTION_UP:
				if (bitmap_style1_isPress)
				{
					if ( bitmapDesRect_style1_s.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						myViewXCZYC.selectstyle = 1;
					}
				}
				//抬起判断是否点击按钮，防止用户移动到别处
				if (bitmap_style2_isPress)
				{
					if ( bitmapDesRect_style2_s.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						myViewXCZYC.selectstyle = 2;
					}
				}
				if (bitmap_style3_isPress)
				{
					if ( bitmapDesRect_style3_s.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						myViewXCZYC.selectstyle = 3;
					}
				}
				
				if (bitmap_confirm_isPress)
				{
					if ( bitmapDesRect_confirm_s.contains(pointX, pointY) ) 
					{
						//还原Button状态为未按下状态
						this.set_is_display(false);
						myViewXCZYC.myViewMain.set_is_display(true);
					}
				}
				
				bitmap_confirm_isPress = false;
				bitmap_style1_isPress = false;
				bitmap_style2_isPress = false;
				bitmap_style3_isPress = false;
				break;
		}
		return true;
	}
}
